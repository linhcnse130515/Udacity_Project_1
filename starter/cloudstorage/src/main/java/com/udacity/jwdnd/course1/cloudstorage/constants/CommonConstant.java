package com.udacity.jwdnd.course1.cloudstorage.constants;

import org.springframework.stereotype.Component;

@Component
public class CommonConstant {
    public CommonConstant() {
    }

    public static final String UPLOAD_CREDENTIAL_ERROR = "There was an error when upload your credential. Please try again!";

    public static final String EDIT_CREDENTIAL_ERROR = "There was an error when edit your credential. Please try again!";

    public static final String DELETE_CREDENTIAL_ERROR = "There was an error when delete your credential. Please try again!";

    public static final String NO_FILE_UPLOAD = "There is no file to upload!";

    public static final String SIZE_TOO_LARGE = "Too large file size to upload. Maximum 1MB allowed!";

    public static final String FILE_EXIST = "Filename already exist!";

    public static final String UPLOAD_FILE_ERROR = "There was an error when upload your file. Please try again!";

    public static final String DELETE_FILE_ERROR = "There was an error when delete your file. Please try again!";

    public static final String UPLOAD_NOTE_ERROR = "There was an error when upload your note. Please try again!";

    public static final String EDIT_NOTE_ERROR = "There was an error when edit your note. Please try again!";

    public static final String DELETE_NOTE_ERROR = "There was an error when delete your note. Please try again!";

    public static final Integer FILE_SIZE_1_MB = 1048576;

    public static final String USER_EXIST = "The username already exists";

    public static final String SIGNUP_ERROR = "There was an error signing you up. Please try again";
}
