package com.vincent.personal.util.backup;

import java.util.ArrayList;

/**
 * Created with IDEA
 * author:vincent
 * Date:2018/11/7
 */
public class TableCollection extends ArrayList<Table> {
    /**
     * Sort tables according to constraints
     */
    public void sort(){
        for(int i = 0 ; i < size(); ){
            boolean corrupted = false;
            for(int j = i + 1; j < size(); j++){
                if(get(i).isReferenced(get(j))){
                    Table table = get(i);
                    remove(table);
                    add(table);
                    corrupted = true;
                    break;
                }
            }
            if(!corrupted){
                i++;
            }
        }
    }
}

