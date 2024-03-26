//package com.example.portfolioapp.Model;
//
//public class ExperienceClass {
//    String JobTitle, Company, JobType, TimeDuration, Skills;
//
//    public ExperienceClass(String jobTitle, String company, String jobType, String timeDuration, String skills) {
//        JobTitle = jobTitle;
//        Company = company;
//        JobType = jobType;
//        TimeDuration = timeDuration;
//        Skills = skills;
//    }
//
//    public String getJobTitle() {
//        return JobTitle;
//    }
//
//    public void setJobTitle(String jobTitle) {
//        JobTitle = jobTitle;
//    }
//
//    public String getCompany() {
//        return Company;
//    }
//
//    public void setCompany(String company) {
//        Company = company;
//    }
//
//    public String getJobType() {
//        return JobType;
//    }
//
//    public void setJobType(String jobType) {
//        JobType = jobType;
//    }
//
//    public String getTimeDuration() {
//        return TimeDuration;
//    }
//
//    public void setTimeDuration(String timeDuration) {
//        TimeDuration = timeDuration;
//    }
//
//    public String getSkills() {
//        return Skills;
//    }
//
//    public void setSkills(String skills) {
//        Skills = skills;
//    }
//}




package com.example.portfolioapp.Model;

public class ExperienceClass {
    String JobTitle, TimeDuration, Skills;

    public ExperienceClass(String jobTitle, String timeDuration, String skills) {
        JobTitle = jobTitle;
        TimeDuration = timeDuration;
        Skills = skills;
    }

    public String getJobTitle() {
        return JobTitle;
    }

    public void setJobTitle(String jobTitle) {
        JobTitle = jobTitle;
    }

    public String getTimeDuration() {
        return TimeDuration;
    }

    public void setTimeDuration(String timeDuration) {
        TimeDuration = timeDuration;
    }

    public String getSkills() {
        return Skills;
    }

    public void setSkills(String skills) {
        Skills = skills;
    }
}
