package com.hf.project.dye.exception;

import com.hf.project.common.exception.InterFaceException;

/**
 * @Description 节点异常
 * @Author fyq
 * @Date 22/04/09 10:37
 **/

public class NoNoteTypeException extends InterFaceException {

    public NoNoteTypeException() {
        super("明细中节点数[NoteTypeId]大于1或者为0");
    }

}
