package com.hf.project.common.filter.upload;

public interface UploadFilter {

    default String getErrorMessage() {
        return null;
    }

    Boolean filter(Object o);

}
