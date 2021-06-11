package com.gcit.Linuxcommand;

public class Craft {
    private String CraftName;
    private  String ImageUrl;
    private String Description;
    public Craft(String craftName,String imageUrl,String description){
        CraftName=craftName;
        ImageUrl=imageUrl;
        Description=description;

    }
    public String getCraftName(){

        return CraftName;
    }

    public void setCraftName(String craftName)
    {

        CraftName = craftName;
    }

    public String getImageUrl() {

        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {

        ImageUrl = imageUrl;
    }
    public String getDescription(){

        return Description;
    }
    public void setDescription(String description){

        Description=description;
    }


    public Craft() {

    }

}

