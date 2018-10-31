package com.dankook.tagme.view.main.drawerMenu;

public class MenuVO {
    private String menuName;
    private int resourceId;

    public MenuVO(String menuName, int resourceId) {
        this.menuName = menuName;
        this.resourceId = resourceId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
}
