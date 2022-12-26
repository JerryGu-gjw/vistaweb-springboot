package lab.vista.vistaweb.entity;

public class Supplies {
    public String id;
    public String name;
    public String type;
    public String storagearea;
    public String totalnum;
    public String borrowablenum;
    public String remark;
    public String category;
    public String time;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getStoragearea() {
        return storagearea;
    }
    public void setStoragearea(String storagearea) {
        this.storagearea = storagearea;
    }
    public String getTotalnum() {
        return totalnum;
    }
    public void setTotalnum(String totalnum) {
        this.totalnum = totalnum;
    }
    public String getBorrowablenum() {
        return borrowablenum;
    }
    public void setBorrowablenum(String borrowablenum) {
        this.borrowablenum = borrowablenum;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
}
