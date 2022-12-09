package com.pcla.advent.models;

import java.util.ArrayList;
import java.util.HashMap;

public class Day07Folder {

    public HashMap<String, Day07Folder> listFolders = new HashMap<>();
    public ArrayList<Integer> files = new ArrayList<>();
    public Integer folderSize = 0;

    public Integer getFolderSize() {
        return folderSize;
    }

    public void setFolderSize(Integer folderSize) {
        this.folderSize = folderSize;
    }

    public ArrayList<Integer> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<Integer> files) {
        this.files = files;
    }

    public HashMap<String, Day07Folder> getListFolders() {
        return listFolders;
    }

    public void setListFolders(HashMap<String, Day07Folder> listFolders) {
        this.listFolders = listFolders;
    }

    
}
