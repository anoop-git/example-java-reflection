package com.ex.reflect;

import org.apache.commons.lang3.StringUtils;

final class PojoNotEmptyExample extends Validated {

    public PojoNotEmptyExample() { super(); }

    private String noValidation;

    @NotEmpty
    private String validation;

    public String getNoValidation() { return this.noValidation; }

    public String getValidation() { return this.validation; }

    public void setNoValidation(String noValidation) { this.noValidation=noValidation; }

    public void setValidation(String validation) { this.validation = validation; }
}
