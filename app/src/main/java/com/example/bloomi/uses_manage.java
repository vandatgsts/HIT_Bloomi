package com.example.bloomi;

public class uses_manage {
    //thong tin lien quan den user
    String email,phone;
    int id;
    Boolean activeFlag,deleteFlag;
    String creatDate;
    String firstName,lastName,gender,birthday,avatarUrl;
    String coverImageUrl,address,authProvider;
    public uses_manage(String email, String phone, int id, Boolean activeFlag, Boolean deleteFlag, String firstName, String lastName, String gender, String birthday, String avatarUrl, String coverImageUrl, String address, String authProvider) {
        this.email = email;
        this.phone = phone;
        this.id = id;
        this.activeFlag = activeFlag;
        this.deleteFlag = deleteFlag;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthday = birthday;
        this.avatarUrl = avatarUrl;
        this.coverImageUrl = coverImageUrl;
        this.address = address;
        this.authProvider = authProvider;
    }

    public uses_manage( int id, Boolean activeFlag, Boolean deleteFlag, String creatDate,String email,String phone, String firstName, String lastName, String gender, String birthday, String avatarUrl, String coverImageUrl, String address, String authProvider) {
        this.email = email;
        this.phone = phone;
        this.id = id;
        this.activeFlag = activeFlag;
        this.deleteFlag = deleteFlag;
        this.creatDate = creatDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthday = birthday;
        this.avatarUrl = avatarUrl;
        this.coverImageUrl = coverImageUrl;
        this.address = address;
        this.authProvider = authProvider;
    }

    @Override
    public String toString() {
        return "uses_manage{" +
                "email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", id=" + id +
                ", activeFlag=" + activeFlag +
                ", deleteFlag=" + deleteFlag +
                ", creatDate='" + creatDate + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday='" + birthday + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", coverImageUrl='" + coverImageUrl + '\'' +
                ", address='" + address + '\'' +
                ", authProvider='" + authProvider + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Boolean activeFlag) {
        this.activeFlag = activeFlag;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(String creatDate) {
        this.creatDate = creatDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAuthProvider() {
        return authProvider;
    }

    public void setAuthProvider(String authProvider) {
        this.authProvider = authProvider;
    }
}
