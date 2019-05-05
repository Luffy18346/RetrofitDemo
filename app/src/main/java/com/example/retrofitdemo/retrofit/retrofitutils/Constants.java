package com.example.retrofitdemo.retrofit.retrofitutils;

public class Constants {
    public interface TimeOut {
        int IMAGE_UPLOAD_CONNECTION_TIMEOUT = 120;
        int IMAGE_UPLOAD_SOCKET_TIMEOUT = 120;
        int SOCKET_TIME_OUT = 60;
        int CONNECTION_TIME_OUT = 60;
    }

    public interface UrlPath {
        String EMERGENCY_SERVICES = "yourUrl/here/{parameter}";
        String GET_SOMETHING_ELSE = "youAnother/url";
    }

    //Need unique flags for all apis in case if hitting multiple apis in same activity/fragment
    public interface ApiFlags {
        String API_GET_REPOS_OF_USER = "API_GET_REPOS_OF_USER";
    }

  public interface ConfigUtils {
        String SERVER_URL = "https://api.github.com";
  }

    public interface ErrorClass {
        String CODE = "code";
        String STATUS = "status";
        String MESSAGE = "message";
        String DEVELOPER_MESSAGE = "developerMessage";
    }
}
