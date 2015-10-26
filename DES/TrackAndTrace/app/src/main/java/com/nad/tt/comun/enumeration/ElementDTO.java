package com.nad.tt.comun.enumeration;

import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Diego on 22/10/2015.
 */
public class ElementDTO {

    private EditText editText;
    private boolean required;
    private int maxVal;
    private int minVal;
    private String regexp;
    private String lblNameElement;
    private TextView txtMsgError;
    private String errortype;

    public ElementDTO(EditText editText, boolean required, int maxVal, int minVal, String regexp, String lblNameElement, TextView txtMsgError, String errortype) {
        this.editText = editText;
        this.required = required;
        this.maxVal = maxVal;
        this.minVal = minVal;
        this.regexp = regexp;
        this.lblNameElement = lblNameElement;
        this.txtMsgError = txtMsgError;
        this.errortype = errortype;
    }

    public ElementDTO(EditText editText, boolean required, String regexp, String lblNameElement, TextView txtMsgError, String errortype) {
        this.editText = editText;
        this.required = required;
        this.regexp = regexp;
        this.lblNameElement= lblNameElement;
        this.txtMsgError = txtMsgError;
        this.errortype = errortype;
    }

    public EditText getEditText() {
        return editText;
    }

    public void setEditText(EditText editText) {
        this.editText = editText;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public int getMaxVal() {
        return maxVal;
    }

    public void setMaxVal(int maxVal) {
        this.maxVal = maxVal;
    }

    public int getMinVal() {
        return minVal;
    }

    public void setMinVal(int minVal) {
        this.minVal = minVal;
    }

    public String getRegexp() {
        return regexp;
    }

    public void setRegexp(String regexp) {
        this.regexp = regexp;
    }

    public String getLblNameElement() {
        return lblNameElement;
    }

    public void setLblNameElement(String lblNameElement) {
        this.lblNameElement = lblNameElement;
    }

    public TextView getTxtMsgError() {
        return txtMsgError;
    }

    public void setTxtMsgError(TextView txtMsgError) {
        this.txtMsgError = txtMsgError;
    }

    public String getErrortype() {
        return errortype;
    }

    public void setErrortype(String errortype) {
        this.errortype = errortype;
    }

    public ElementDTO clone(ElementDTO elementDTO) {
        elementDTO.setEditText(this.editText);
        elementDTO.setMaxVal(this.maxVal);
        elementDTO.setMinVal(this.minVal);
        elementDTO.setRegexp(this.regexp);
        elementDTO.setRequired(this.required);
        elementDTO.setLblNameElement(this.lblNameElement);
        elementDTO.setTxtMsgError(this.txtMsgError);
        elementDTO.setErrortype(this.errortype);
        return elementDTO;

    }
}
