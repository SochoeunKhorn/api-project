package com.example.server.constants;

public interface Constants {
    String STATUS_ACTIVE = "ACT";
    String STATUS_DELETE = "DEL";
    String STATUS_DISABLE = "DIS";

    String NOT_FOUND = "ERR-404";

    String REQUIRED = "ERR-500";

    String EXISTING = "ERR-501";

    public static final String PHOTO_DIRECTORY = System.getProperty("user.home") + "/Downloads/uploads/";
}
