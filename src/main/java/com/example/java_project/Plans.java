package com.example.java_project;

public class Plans {

        private String  idText,ageText,nameText, surnameText, addressText, Selected;
        private double premiumText, CovText;
        private Integer sumText;

        public  Plans(int idText, String nameText, String surnameText, String addressText,
                     Integer ageText, String Selected, int sumText,double CovText,double premiumText) {
            this.idText = String.valueOf(idText);
            this.nameText = nameText;
            this.surnameText = surnameText;
            this.ageText = String.valueOf(ageText);
            this.addressText = addressText;
            this.sumText = sumText;
            this.CovText = CovText;
            this.Selected = Selected;
            this.premiumText = premiumText;

        }

        public String getName() {
            return nameText;
        }

        public void setName(String nameText) {
            this.nameText = nameText;
        }

        public String getId_numbers() {
            return idText;
        }

        public void setId_numbers(int idText) {
            this.idText = String.valueOf( idText);
        }

        public String getSurname() {
            return surnameText;
        }

        public void setSurname(String surnameText) {
            this.surnameText = surnameText;
        }

        public String getAge() {
            return ageText;
        }

        public void setAge(Integer ageText) {
            this.ageText = String.valueOf(ageText);
        }

        public String getAddress() {
            return addressText;
        }

        public void setAddress(String addressText) {
            this.addressText = addressText;
        }

        public double getSum() {
            return sumText;
        }

        public void setSum(int sumText) {
            this.sumText = sumText;
        }

        public double getCoverage() {
            return CovText;
        }

        public void setCoverage(double CovText) {
            this.CovText = CovText;
        }

        public String getPolicy_type() {
            return Selected;
        }

        public void setPolicy_type(String Selected) {
            this.Selected = Selected;
        }

        public double getPremium() {
            return premiumText;
        }

        public void setPremium(double premiumText) {
            this.premiumText = premiumText;
        }




    }


