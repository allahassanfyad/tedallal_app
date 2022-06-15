package com.application.tedallal_app.Scenarios.ScenarioChat.Model;

public class Organization_Details {

    public String organization_name;
    public int organization_id;


    public Organization_Details() {
    }

    public Organization_Details(String organization_name, int organization_id) {
        this.organization_name = organization_name;
        this.organization_id = organization_id;
    }

    public String getOrganization_name() {
        return organization_name;
    }

    public void setOrganization_name(String organization_name) {
        this.organization_name = organization_name;
    }

    public int getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(int organization_id) {
        this.organization_id = organization_id;
    }
}
