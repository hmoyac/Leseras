/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.siss.web.upload;

/**
 *
 * @author hector
 */
/* MPL License text (see http://www.mozilla.org/MPL/) */

import com.icesoft.faces.component.inputfile.InputFile;
import com.icesoft.faces.webapp.xmlhttp.FatalRenderingException;
import com.icesoft.faces.webapp.xmlhttp.RenderingException;
import com.icesoft.faces.webapp.xmlhttp.TransientRenderingException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.util.*;
import java.io.Serializable;

/**
 * <p>The InputFileController is responsible for the file upload
 * logic as well as the file deletion object.  A users file uploads are only
 * visible to them and are deleted when the session is destroyed.</p>
 *
 * @since 1.7
 */
//        implements Renderable, DisposableBean, Serializable {
public class InputFileController implements Serializable {

    public static final Log log = LogFactory.getLog(InputFileController.class);

    // File sizes used to generate formatted label
    public static final long MEGABYTE_LENGTH_BYTES = 1048000l;
    public static final long KILOBYTE_LENGTH_BYTES = 1024l;

    // render manager for the application, uses session id for on demand
    // render group.
//    private RenderManager renderManager;
//    private PersistentFacesState persistentFacesState;
//    private String sessionId;

    // files associated with the current user
    private final List fileList =
            Collections.synchronizedList(new ArrayList());
    // latest file uploaded by client
    private InputFileData currentFile;
    // file upload completed percent (Progress)
    private int fileProgress;

    public InputFileController() {
//        persistentFacesState = PersistentFacesState.getInstance();

        // Get the session id in a container generic way
//        sessionId = FacesContext.getCurrentInstance().getExternalContext()
//                .getSession(false).toString();
    }

    /**
     * <p>Action event method which is triggered when a user clicks on the
     * upload file button.  Uploaded files are added to a list so that user have
     * the option to delete them programatically.  Any errors that occurs
     * during the file uploaded are added the messages output.</p>
     *
     * @param event jsf action event.
     */
    public void uploadFile(ActionEvent event) {
        InputFile inputFile = (InputFile) event.getSource();
        if (inputFile.getStatus() == InputFile.SAVED) {
            // reference our newly updated file for display purposes and
            // added it to our history file list.
            currentFile = new InputFileData(inputFile.getFileInfo(),
                    inputFile.getFile());

            synchronized (fileList) {
                fileList.add(currentFile);
            }

        }
//		//invalid file, happens when clicking on upload without selecting a file, or a file with no contents.
//         if (inputFile.getStatus() == InputFile.INVALID) {
//             componentStatus = "INVALID";
//         }
//       //file size exceeded the limit
//        if (inputFile.getStatus() == InputFile.SIZE_LIMIT_EXCEEDED) {
//            componentStatus = "SIZE_LIMIT_EXCEEDED";
//        }
//       //indicate that the request size is not specified.
//        if (inputFile.getStatus() == InputFile.UNKNOWN_SIZE) {
//            componentStatus = "UNKNOWN_SIZE";
//       }

    }

    /**
     * <p>This method is bound to the inputFile component and is executed
     * multiple times during the file upload process.  Every call allows
     * the user to finds out what percentage of the file has been uploaded.
     * This progress information can then be used with a progressBar component
     * for user feedback on the file upload progress. </p>
     *
     * @param event holds a InputFile object in its source which can be probed
     *              for the file upload percentage complete.
     */
    public void fileUploadProgress(EventObject event) {
        InputFile ifile = (InputFile) event.getSource();
        fileProgress = ifile.getFileInfo().getPercent();
//        renderManager.getOnDemandRenderer(sessionId).requestRender();
    }

    /**
     * <p>Allows a user to remove a file from a list of uploaded files.  This
     * methods assumes that a request param "fileName" has been set to a valid
     * file name that the user wishes to remove or delete</p>
     *
     * @param event jsf action event
     */
    public void removeUploadedFile(ActionEvent event) {
        // Get the inventory item ID from the context.
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        String fileName = (String) map.get("fileName");

        synchronized (fileList) {
            InputFileData inputFileData;
            for (int i = 0; i < fileList.size(); i++) {
                inputFileData = (InputFileData)fileList.get(i);
                // remove our file
                if (inputFileData.getFileInfo().getFileName().equals(fileName)) {
                    fileList.remove(i);
                    break;
                }
            }
        }
    }

    /**
     * Callback method that is called if any exception occurs during an attempt
     * to render this Renderable.
     * <p/>
     * It is up to the application developer to implement appropriate policy
     * when a RenderingException occurs.  Different policies might be
     * appropriate based on the severity of the exception.  For example, if the
     * exception is fatal (the session has expired), no further attempts should
     * be made to render this Renderable and the application may want to remove
     * the Renderable from some or all of the
     * {@link com.icesoft.faces.async.render.GroupAsyncRenderer}s it
     * belongs to. If it is a transient exception (like a client's connection is
     * temporarily unavailable) then the application has the option of removing
     * the Renderable from GroupRenderers or leaving them and allowing another
     * render call to be attempted.
     *
     * @param renderingException The exception that occurred when attempting to
     *                           render this Renderable.
     */
    public void renderingException(RenderingException renderingException) {
        if (log.isTraceEnabled() &&
                renderingException instanceof TransientRenderingException) {
            log.trace("InputFileController Transient Rendering excpetion:", renderingException);
        } else if (renderingException instanceof FatalRenderingException) {
            if (log.isTraceEnabled()) {
                log.trace("InputFileController Fatal rendering exception: ", renderingException);
            }
//            renderManager.getOnDemandRenderer(sessionId).remove(this);
//            renderManager.getOnDemandRenderer(sessionId).dispose();
        }
    }

//    /**
//     * Return the reference to the
//     * {@link com.icesoft.faces.webapp.xmlhttp.PersistentFacesState
//     * PersistentFacesState} associated with this Renderable.
//     * <p/>
//     * The typical (and recommended usage) is to get and hold a reference to the
//     * PersistentFacesState in the constructor of your managed bean and return
//     * that reference from this method.
//     *
//     * @return the PersistentFacesState associated with this Renderable
//     */
//    public PersistentFacesState getState() {
//        return persistentFacesState;
//    }

//    public void setRenderManager(RenderManager renderManager) {
//        this.renderManager = renderManager;
//        renderManager.getOnDemandRenderer(sessionId).add(this);
//    }

    public InputFileData getCurrentFile() {
        return currentFile;
    }

    public int getFileProgress() {
        return fileProgress;
    }

    public List getFileList() {
        return fileList;
    }

//    /**
//     * Dispose callback called due to a view closing or session
//     * invalidation/timeout
//     */
//	public void dispose() throws Exception {
//        if (log.isTraceEnabled()) {
//            log.trace("OutputProgressController dispose OnDemandRenderer for session: " + sessionId);
//        }
//        renderManager.getOnDemandRenderer(sessionId).remove(this);
//		renderManager.getOnDemandRenderer(sessionId).dispose();
//	}
}