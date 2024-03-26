package com.example.portfolioapp.Model;

public class CertificateClass {
    String Image, Name, IssueOrganization, IssueDateYear, Skill;
    public CertificateClass(String image, String name, String issueOrganization, String issueDateYear, String skill) {
        Image = image;
        Name = name;
        IssueOrganization = issueOrganization;
        IssueDateYear = issueDateYear;
        Skill = skill;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getIssueOrganization() {
        return IssueOrganization;
    }

    public void setIssueOrganization(String issueOrganization) {
        IssueOrganization = issueOrganization;
    }

    public String getIssueDateYear() {
        return IssueDateYear;
    }

    public void setIssueDateYear(String issueDateYear) {
        IssueDateYear = issueDateYear;
    }

    public String getSkill() {
        return Skill;
    }

    public void setSkill(String skill) {
        Skill = skill;
    }
}
