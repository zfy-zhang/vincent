package com.vincent.personal.util.backup;

import java.util.ArrayList;

/**
 * Created with IDEA
 * author:vincent
 * Date:2018/11/7
 */
public class FKCollection extends ArrayList<FK> {
    private static final long serialVersionUID = -972085209611643212L;

    public boolean isReferenced(Table referenceTable) {
        for (FK fk : this) {
            if (fk.getReferenceTable().equals(referenceTable)) {
                return true;
            }
        }
        return false;
    }
}