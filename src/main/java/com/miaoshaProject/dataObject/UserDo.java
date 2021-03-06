package com.miaoshaProject.dataObject;

public class UserDo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_Info.id
     *
     * @mbg.generated Sat Jun 27 00:30:16 EDT 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_Info.name
     *
     * @mbg.generated Sat Jun 27 00:30:16 EDT 2020
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_Info.gender
     *
     * @mbg.generated Sat Jun 27 00:30:16 EDT 2020
     */
    private Integer gender;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_Info.age
     *
     * @mbg.generated Sat Jun 27 00:30:16 EDT 2020
     */
    private Integer age;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_Info.telphone
     *
     * @mbg.generated Sat Jun 27 00:30:16 EDT 2020
     */
    private String telphone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_Info.register_mode
     *
     * @mbg.generated Sat Jun 27 00:30:16 EDT 2020
     */
    private String registerMode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_Info.third_party_id
     *
     * @mbg.generated Sat Jun 27 00:30:16 EDT 2020
     */
    private String thirdPartyId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_Info.id
     *
     * @return the value of user_Info.id
     *
     * @mbg.generated Sat Jun 27 00:30:16 EDT 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_Info.id
     *
     * @param id the value for user_Info.id
     *
     * @mbg.generated Sat Jun 27 00:30:16 EDT 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_Info.name
     *
     * @return the value of user_Info.name
     *
     * @mbg.generated Sat Jun 27 00:30:16 EDT 2020
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_Info.name
     *
     * @param name the value for user_Info.name
     *
     * @mbg.generated Sat Jun 27 00:30:16 EDT 2020
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_Info.gender
     *
     * @return the value of user_Info.gender
     *
     * @mbg.generated Sat Jun 27 00:30:16 EDT 2020
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_Info.gender
     *
     * @param gender the value for user_Info.gender
     *
     * @mbg.generated Sat Jun 27 00:30:16 EDT 2020
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_Info.age
     *
     * @return the value of user_Info.age
     *
     * @mbg.generated Sat Jun 27 00:30:16 EDT 2020
     */
    public Integer getAge() {
        return age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_Info.age
     *
     * @param age the value for user_Info.age
     *
     * @mbg.generated Sat Jun 27 00:30:16 EDT 2020
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_Info.telphone
     *
     * @return the value of user_Info.telphone
     *
     * @mbg.generated Sat Jun 27 00:30:16 EDT 2020
     */
    public String getTelphone() {
        return telphone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_Info.telphone
     *
     * @param telphone the value for user_Info.telphone
     *
     * @mbg.generated Sat Jun 27 00:30:16 EDT 2020
     */
    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_Info.register_mode
     *
     * @return the value of user_Info.register_mode
     *
     * @mbg.generated Sat Jun 27 00:30:16 EDT 2020
     */
    public String getRegisterMode() {
        return registerMode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_Info.register_mode
     *
     * @param registerMode the value for user_Info.register_mode
     *
     * @mbg.generated Sat Jun 27 00:30:16 EDT 2020
     */
    public void setRegisterMode(String registerMode) {
        this.registerMode = registerMode == null ? null : registerMode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_Info.third_party_id
     *
     * @return the value of user_Info.third_party_id
     *
     * @mbg.generated Sat Jun 27 00:30:16 EDT 2020
     */
    public String getThirdPartyId() {
        return thirdPartyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_Info.third_party_id
     *
     * @param thirdPartyId the value for user_Info.third_party_id
     *
     * @mbg.generated Sat Jun 27 00:30:16 EDT 2020
     */
    public void setThirdPartyId(String thirdPartyId) {
        this.thirdPartyId = thirdPartyId == null ? null : thirdPartyId.trim();
    }
}