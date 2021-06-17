
package com.example.onlinedirectoryprovider.model.resetpassword;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Result {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("first_name")
    @Expose
    private Object firstName;
    @SerializedName("last_name")
    @Expose
    private Object lastName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("roles_id")
    @Expose
    private Integer rolesId;
    @SerializedName("template_id")
    @Expose
    private Integer templateId;
    @SerializedName("otp")
    @Expose
    private Integer otp;
    @SerializedName("request_otp")
    @Expose
    private Integer requestOtp;
    @SerializedName("verified")
    @Expose
    private Integer verified;
    @SerializedName("email_verified_at")
    @Expose
    private Object emailVerifiedAt;
    @SerializedName("profile_image")
    @Expose
    private Object profileImage;
    @SerializedName("business_name")
    @Expose
    private String businessName;
    @SerializedName("slug")
    @Expose
    private Object slug;
    @SerializedName("sms_gateway")
    @Expose
    private Object smsGateway;
    @SerializedName("address")
    @Expose
    private Object address;
    @SerializedName("country")
    @Expose
    private Object country;
    @SerializedName("state")
    @Expose
    private Object state;
    @SerializedName("city")
    @Expose
    private Object city;
    @SerializedName("zip_code")
    @Expose
    private String zipCode;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
    @SerializedName("device_type")
    @Expose
    private Object deviceType;
    @SerializedName("device_token")
    @Expose
    private Object deviceToken;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getFirstName() {
        return firstName;
    }

    public void setFirstName(Object firstName) {
        this.firstName = firstName;
    }

    public Object getLastName() {
        return lastName;
    }

    public void setLastName(Object lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getRolesId() {
        return rolesId;
    }

    public void setRolesId(Integer rolesId) {
        this.rolesId = rolesId;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public Integer getOtp() {
        return otp;
    }

    public void setOtp(Integer otp) {
        this.otp = otp;
    }

    public Integer getRequestOtp() {
        return requestOtp;
    }

    public void setRequestOtp(Integer requestOtp) {
        this.requestOtp = requestOtp;
    }

    public Integer getVerified() {
        return verified;
    }

    public void setVerified(Integer verified) {
        this.verified = verified;
    }

    public Object getEmailVerifiedAt() {
        return emailVerifiedAt;
    }

    public void setEmailVerifiedAt(Object emailVerifiedAt) {
        this.emailVerifiedAt = emailVerifiedAt;
    }

    public Object getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Object profileImage) {
        this.profileImage = profileImage;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public Object getSlug() {
        return slug;
    }

    public void setSlug(Object slug) {
        this.slug = slug;
    }

    public Object getSmsGateway() {
        return smsGateway;
    }

    public void setSmsGateway(Object smsGateway) {
        this.smsGateway = smsGateway;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Object getCountry() {
        return country;
    }

    public void setCountry(Object country) {
        this.country = country;
    }

    public Object getState() {
        return state;
    }

    public void setState(Object state) {
        this.state = state;
    }

    public Object getCity() {
        return city;
    }

    public void setCity(Object city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Object getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Object deviceType) {
        this.deviceType = deviceType;
    }

    public Object getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(Object deviceToken) {
        this.deviceToken = deviceToken;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
