package sec03.brd04;

import java.sql.Date;

public class ArticleDTO {
	private int level;
	private int articleNO;
	private int parentNO;
	private String title;
	private String content;
	private String id;
	private String imageFileName;
	private Date writeDate;
	

	public ArticleDTO() {
		
	}
	
	//DAO selectAllArticles query
	public ArticleDTO(int level, int articleNO, String title, String content, String id, Date writeDate ) {
	    this.level = level;
	    this.articleNO = articleNO;
	    this.title = title;
	    this.content = content;
	    this.id = id;
	    this.writeDate = writeDate;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getArticleNO() {
		return articleNO;
	}

	public void setArticleNO(int articleNO) {
		this.articleNO = articleNO;
	}

	public int getParentNO() {
		return parentNO;
	}

	public void setParentNO(int parentNO) {
		this.parentNO = parentNO;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
}
