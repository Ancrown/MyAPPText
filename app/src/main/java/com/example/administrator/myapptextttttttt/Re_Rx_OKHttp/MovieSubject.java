package com.example.administrator.myapptextttttttt.Re_Rx_OKHttp;

import com.example.administrator.myapptextttttttt.Re_Rx_OKHttp.bean.Movie;

import java.util.List;

/**
 * Created by zhouwei on 16/11/9.
 */

public class MovieSubject {
   public int count;
   public int start;
   public int total;
   public List<Movie> subjects;
   public String title;

   public int getCount() {
      return count;
   }

   public void setCount(int count) {
      this.count = count;
   }

   public int getStart() {
      return start;
   }

   public void setStart(int start) {
      this.start = start;
   }

   public int getTotal() {
      return total;
   }

   public void setTotal(int total) {
      this.total = total;
   }

   public List<Movie> getSubjects() {
      return subjects;
   }

   public void setSubjects(List<Movie> subjects) {
      this.subjects = subjects;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   @Override
   public String toString() {
      return "MovieSubject{" +
              "count=" + count +
              ", start=" + start +
              ", total=" + total +
              ", subjects=" + subjects +
              ", title='" + title + '\'' +
              '}';
   }
}
