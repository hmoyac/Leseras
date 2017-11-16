package com.siss.xades;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.crypto.dom.DOMStructure;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLObject;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import com.sun.org.apache.xml.internal.security.utils.IdResolver;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;

/**
 *
 * @author econtreras
 */
public class Signer {

    public static String xadesNS = null;//"http://uri.etsi.org/01903/v1.3.2#";
    public static String signatureID = "Sig1";
    public static String signedPropID = "SignP";

    public static void main(String[] arg) {
        try {

            XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM", new org.jcp.xml.dsig.internal.dom.XMLDSigRI());
            List<Reference> refs = new ArrayList<Reference>();
            Reference ref1 = fac.newReference("", fac.newDigestMethod(DigestMethod.SHA1, null),
                    Collections.singletonList(fac.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null)),
                    null, null);
            refs.add(ref1);
            Reference ref2 = fac.newReference("#" + signedPropID, fac.newDigestMethod(DigestMethod.SHA1, null), null, "http://uri.etsi.org/01903/v1.3.2#SignedProperties", null);
            refs.add(ref2);

            SignedInfo si = fac.newSignedInfo
            (fac.newCanonicalizationMethod
            (CanonicalizationMethod.INCLUSIVE_WITH_COMMENTS, 
            (C14NMethodParameterSpec) null), 
            fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
            refs);

            String sKeyStore = "keystore.keystore";
            String sKeyStorePass = "s.i.s.s.2009";
            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
            FileInputStream fisKS = new FileInputStream(sKeyStore);
            ks.load(fisKS, sKeyStorePass.toCharArray());
            X509Certificate cert = (X509Certificate) ks.getCertificate("sisskey");
            PrivateKey key = (PrivateKey) ks.getKey("sisskey", "s.i.s.s.2009".toCharArray());
            PublicKey keyPublic = cert.getPublicKey();
            //System.out.println("Llave Privada");
            //System.out.println(key.toString());
            //System.out.println("Llave Pública");
            //System.out.println(keyPublic.toString());

            KeyInfoFactory kifRSA = fac.getKeyInfoFactory();
            KeyValue kv = kifRSA.newKeyValue(keyPublic);
            KeyInfo kiRSA = kifRSA.newKeyInfo(Collections.singletonList(kv));

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            Document doc = dbf.newDocumentBuilder().parse("purchaseOrder.xml");

            DOMSignContext dscRSA = new DOMSignContext(key, doc.getDocumentElement());
            dscRSA.putNamespacePrefix(XMLSignature.XMLNS, "ds");

            Element QPElement = createElement(doc, "QualifyingProperties",null,xadesNS);
            QPElement.setAttributeNS(null, "Target", signatureID);

            Element SPElement = createElement(doc, "SignedProperties", null,xadesNS);
            SPElement.setAttributeNS(null, "Id", signedPropID);
            IdResolver.registerElementById(SPElement, signedPropID);
            QPElement.appendChild(SPElement);

            Element UPElement = createElement(doc, "UnsignedProperties", null,xadesNS);
            QPElement.appendChild(UPElement);

            DOMStructure qualifPropStruct = new DOMStructure(QPElement);

            List<DOMStructure> xmlObj = new ArrayList<DOMStructure>();
            xmlObj.add(qualifPropStruct);
            XMLObject object = fac.newXMLObject(xmlObj,"QualifyingInfos",null,null);

            List objects = Collections.singletonList(object);

            XMLSignature signatureRSA = fac.newXMLSignature(si, kiRSA, objects, "signatureID", null);

            signatureRSA.sign(dscRSA);

            OutputStream os = new FileOutputStream("signedPurchaseOrder.xml");
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer trans = tf.newTransformer();
            trans.transform(new DOMSource(doc), new StreamResult(os));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Validator.main(null);
        } catch (Exception e) {
            System.out.println("Validator exception");
            e.printStackTrace();
        }
    }

    public static Element createElement(Document doc, String tag, String prefix, String nsURI) {
        String qName = prefix == null ? tag : prefix + ":" + tag;
        return doc.createElementNS(nsURI, qName);
    }
}