package com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAddAddress.Model;

public class ModelCountries {
    private String countryAr;
    private String countryEn;
    private String countryCode;

    public ModelCountries() {
    }

    public ModelCountries(String countryAr, String countryEn, String countryCode) {
        this.countryAr = countryAr;
        this.countryEn = countryEn;
        this.countryCode = countryCode;
    }

    public String getCountryAr() {
        return countryAr;
    }

    public void setCountryAr(String countryAr) {
        this.countryAr = countryAr;
    }

    public String getCountryEn() {
        return countryEn;
    }

    public void setCountryEn(String countryEn) {
        this.countryEn = countryEn;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
