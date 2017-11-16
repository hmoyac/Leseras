package com.siss.web.bean;

import com.siss.web.model.ModeloExcel;


/**
 *
 * @author hmoya
 */
public class ExportarExcelBean {

    private ModeloExcel modeloExcel = null;

    /** Creates a new instance of ExportarExcelBean */
    public ExportarExcelBean() {
    }

    public ModeloExcel getModeloExcel() {
        return modeloExcel;
    }

    public void setModeloExcel(ModeloExcel modeloExcel) {
        this.modeloExcel = modeloExcel;
    }

}
