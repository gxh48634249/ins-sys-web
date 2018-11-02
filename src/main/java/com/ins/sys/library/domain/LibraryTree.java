package com.ins.sys.library.domain;

import java.util.List;

public class LibraryTree extends LibraryInfoEntity{


    private List<LibraryInfoEntity> child;

    public List<LibraryInfoEntity> getChild() {
        return child;
    }

    public void setChild(List<LibraryInfoEntity> child) {
        this.child = child;
    }

    public LibraryTree(LibraryInfoEntity libraryInfoEntity){
        this.setLibraryId(libraryInfoEntity.getLibraryId());
        this.setCreateTime(libraryInfoEntity.getCreateTime());
        this.setLibraryCode(libraryInfoEntity.getLibraryCode());
        this.setLibraryName(libraryInfoEntity.getLibraryName());
        this.setParentCode(libraryInfoEntity.getParentCode());
    }
}
