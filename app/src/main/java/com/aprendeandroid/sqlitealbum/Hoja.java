package com.aprendeandroid.sqlitealbum;

import android.database.Cursor;

public class Hoja {

	private long id;
	private String title;
	private long lastTime;

	public Hoja(long id, long lastTime, String comment) {
		this.id = id;
		this.title = comment;
		this.lastTime = lastTime;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String comment) {
		this.title = comment;
	}
	
	public long getLastTime() {
		return lastTime;
	}

	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}

	public static Hoja cursorToHoja(Cursor cursor) {
		Hoja hoja = new Hoja(cursor.getLong(0), cursor.getLong(1),
				cursor.getString(2));
		return hoja;
	}
}
