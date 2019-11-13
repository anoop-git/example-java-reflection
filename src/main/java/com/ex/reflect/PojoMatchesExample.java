package com.ex.reflect;

final class PojoMatchesExample extends Validated {

    public PojoMatchesExample() { super(); }

    private String noMatch;

    @Matches("^[0-9]{3}$")
    private String doMatch;

    public String getNoMatch() { return this.noMatch; }
    public String getDoMatch() { return this.doMatch; }
    public void setNoMatch(String noMatch) { this.noMatch = noMatch; }
    public void setDoMatch(String doMatch) { this.doMatch = doMatch; }
}
