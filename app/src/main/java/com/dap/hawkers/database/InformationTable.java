package com.dap.hawkers.database;

import com.orm.SugarRecord;

/**
 * Created by AABID on 08-11-2014.
 */
public class InformationTable extends SugarRecord<InformationTable> {

    String optionType;
    String state;
    String district;
    String city;
    String area;

    String image_string;
    String name;
    String father_name;
    String permanent_address;
    String present_address;
    String mobile_number;
    String email;
    String age;
    String gender;
    String identity_proof;
    String identity_proof_details;
    String qualification;
    String qualification_details;
    String religion;
    String cast;
    String marital_status;
    boolean bpl_card;
    boolean health_card;
    boolean indira_aawas;
    boolean rajeev_aawas;
    boolean tpds;
    boolean sjsry;
    boolean food_security;
    boolean widow_pension;
    boolean oldage_pension;
    boolean manrega;
    boolean ladali_yogna;
    boolean saraswati_yogna;

    String business;
    String business_type;
    String avg_daily_income;

    public InformationTable(String optionType, String state, String district, String city, String area) {
        this.optionType = optionType;
        this.state = state;
        this.district = district;
        this.city = city;
        this.area = area;
    }

    public InformationTable() {
    }


    public String getOptionType() {
        return optionType;
    }

    public void setOptionType(String optionType) {
        this.optionType = optionType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getImage_string() {
        return image_string;
    }

    public void setImage_string(String image_string) {
        this.image_string = image_string;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public String getPermanent_address() {
        return permanent_address;
    }

    public void setPermanent_address(String permanent_address) {
        this.permanent_address = permanent_address;
    }

    public String getPresent_address() {
        return present_address;
    }

    public void setPresent_address(String present_address) {
        this.present_address = present_address;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdentity_proof() {
        return identity_proof;
    }

    public void setIdentity_proof(String identity_proof) {
        this.identity_proof = identity_proof;
    }

    public String getIdentity_proof_details() {
        return identity_proof_details;
    }

    public void setIdentity_proof_details(String identity_proof_details) {
        this.identity_proof_details = identity_proof_details;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getQualification_details() {
        return qualification_details;
    }

    public void setQualification_details(String qualification_details) {
        this.qualification_details = qualification_details;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }

    public boolean isBpl_card() {
        return bpl_card;
    }

    public void setBpl_card(boolean bpl_card) {
        this.bpl_card = bpl_card;
    }

    public boolean isHealth_card() {
        return health_card;
    }

    public void setHealth_card(boolean health_card) {
        this.health_card = health_card;
    }

    public boolean isIndira_aawas() {
        return indira_aawas;
    }

    public void setIndira_aawas(boolean indira_aawas) {
        this.indira_aawas = indira_aawas;
    }

    public boolean isRajeev_aawas() {
        return rajeev_aawas;
    }

    public void setRajeev_aawas(boolean rajeev_aawas) {
        this.rajeev_aawas = rajeev_aawas;
    }

    public boolean isTpds() {
        return tpds;
    }

    public void setTpds(boolean tpds) {
        this.tpds = tpds;
    }

    public boolean isSjsry() {
        return sjsry;
    }

    public void setSjsry(boolean sjsry) {
        this.sjsry = sjsry;
    }

    public boolean isFood_security() {
        return food_security;
    }

    public void setFood_security(boolean food_security) {
        this.food_security = food_security;
    }

    public boolean isWidow_pension() {
        return widow_pension;
    }

    public void setWidow_pension(boolean widow_pension) {
        this.widow_pension = widow_pension;
    }

    public boolean isOldage_pension() {
        return oldage_pension;
    }

    public void setOldage_pension(boolean oldage_pension) {
        this.oldage_pension = oldage_pension;
    }

    public boolean isManrega() {
        return manrega;
    }

    public void setManrega(boolean manrega) {
        this.manrega = manrega;
    }

    public boolean isLadali_yogna() {
        return ladali_yogna;
    }

    public void setLadali_yogna(boolean ladali_yogna) {
        this.ladali_yogna = ladali_yogna;
    }

    public boolean isSaraswati_yogna() {
        return saraswati_yogna;
    }

    public void setSaraswati_yogna(boolean saraswati_yogna) {
        this.saraswati_yogna = saraswati_yogna;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getBusiness_type() {
        return business_type;
    }

    public void setBusiness_type(String business_type) {
        this.business_type = business_type;
    }

    public String getAvg_daily_income() {
        return avg_daily_income;
    }

    public void setAvg_daily_income(String avg_daily_income) {
        this.avg_daily_income = avg_daily_income;
    }

    @Override
    public String toString() {
        return "InformationTable{" +
                "optionType='" + optionType + '\'' +
                ", state='" + state + '\'' +
                ", district='" + district + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", image_string='" + image_string + '\'' +
                ", name='" + name + '\'' +
                ", father_name='" + father_name + '\'' +
                ", permanent_address='" + permanent_address + '\'' +
                ", present_address='" + present_address + '\'' +
                ", mobile_number='" + mobile_number + '\'' +
                ", email='" + email + '\'' +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                ", identity_proof='" + identity_proof + '\'' +
                ", identity_proof_details='" + identity_proof_details + '\'' +
                ", qualification='" + qualification + '\'' +
                ", qualification_details='" + qualification_details + '\'' +
                ", religion='" + religion + '\'' +
                ", cast='" + cast + '\'' +
                ", marital_status='" + marital_status + '\'' +
                ", bpl_card=" + bpl_card +
                ", health_card=" + health_card +
                ", indira_aawas=" + indira_aawas +
                ", rajeev_aawas=" + rajeev_aawas +
                ", tpds=" + tpds +
                ", sjsry=" + sjsry +
                ", food_security=" + food_security +
                ", widow_pension=" + widow_pension +
                ", oldage_pension=" + oldage_pension +
                ", manrega=" + manrega +
                ", ladali_yogna=" + ladali_yogna +
                ", saraswati_yogna=" + saraswati_yogna +
                ", business='" + business + '\'' +
                ", business_type='" + business_type + '\'' +
                ", avg_daily_income='" + avg_daily_income + '\'' +
                '}';
    }
}
