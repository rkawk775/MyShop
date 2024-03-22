
public class MenuDTO {
	private String title;
	private String image;
	private String memo;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	@Override
	public String toString() {
		return "MenuDTO [title=" + title + ", image=" + image + ", meno=" + memo + "]";
	}
	
	
}
