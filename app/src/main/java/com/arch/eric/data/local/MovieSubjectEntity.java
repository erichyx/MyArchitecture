package com.arch.eric.data.local;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by eric on 2018/5/31
 */
public class MovieSubjectEntity {

    /**
     * count : 20
     * start : 0
     * total : 15
     * subjects : [{"rating":{"max":10,"average":7.2,"stars":"40","min":0},"genres":["喜剧","奇幻"],"title":"超时空同居","casts":[{"alt":"https://movie.douban.com/celebrity/1312940/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1503986232.61.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1503986232.61.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1503986232.61.jpg"},"name":"雷佳音","id":"1312940"},{"alt":"https://movie.douban.com/celebrity/1275756/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1367210981.95.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1367210981.95.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1367210981.95.jpg"},"name":"佟丽娅","id":"1275756"},{"alt":"https://movie.douban.com/celebrity/1325751/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1499435823.67.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1499435823.67.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1499435823.67.jpg"},"name":"张衣","id":"1325751"}],"collectCount":80453,"originalTitle":"超时空同居","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1331887/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1520837465.56.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1520837465.56.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1520837465.56.jpg"},"name":"苏伦","id":"1331887"}],"year":"2018","images":{"small":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2520331478.jpg","large":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2520331478.jpg","medium":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2520331478.jpg"},"alt":"https://movie.douban.com/subject/27133303/","id":"27133303"}]
     * title : 正在上映的电影-厦门
     */

    private int count;
    private int start;
    private int total;
    private String title;
    private List<SubjectsBean> subjects;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SubjectsBean> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectsBean> subjects) {
        this.subjects = subjects;
    }

    @Entity(tableName = "tb_movie", indices = {@Index(value = "subjectId", unique = true)})
    public static class SubjectsBean {
        /**
         * rating : {"max":10,"average":7.2,"stars":"40","min":0}
         * genres : ["喜剧","奇幻"]
         * title : 超时空同居
         * casts : [{"alt":"https://movie.douban.com/celebrity/1312940/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1503986232.61.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1503986232.61.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1503986232.61.jpg"},"name":"雷佳音","id":"1312940"},{"alt":"https://movie.douban.com/celebrity/1275756/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1367210981.95.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1367210981.95.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1367210981.95.jpg"},"name":"佟丽娅","id":"1275756"},{"alt":"https://movie.douban.com/celebrity/1325751/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1499435823.67.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1499435823.67.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1499435823.67.jpg"},"name":"张衣","id":"1325751"}]
         * collectCount : 80453
         * originalTitle : 超时空同居
         * subtype : movie
         * directors : [{"alt":"https://movie.douban.com/celebrity/1331887/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1520837465.56.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1520837465.56.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1520837465.56.jpg"},"name":"苏伦","id":"1331887"}]
         * year : 2018
         * images : {"small":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2520331478.jpg","large":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2520331478.jpg","medium":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2520331478.jpg"}
         * alt : https://movie.douban.com/subject/27133303/
         * id : 27133303
         */

        @Embedded
        private RatingBean rating;
        private String title;
        @SerializedName("collect_count")
        private int collectCount;
        @SerializedName("original_title")
        private String originalTitle;
        private String subtype;
        private String year;
        @Embedded
        private ImagesBean images;
        private String alt;

        @PrimaryKey(autoGenerate = true)
        private int autoId;

        @SerializedName("id")
        private int subjectId;

        @Ignore
        private List<String> genres;
        @Ignore
        private List<CastsBean> casts;
        @Ignore
        private List<DirectorsBean> directors;

        public RatingBean getRating() {
            return rating;
        }

        public void setRating(RatingBean rating) {
            this.rating = rating;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getCollectCount() {
            return collectCount;
        }

        public void setCollectCount(int collectCount) {
            this.collectCount = collectCount;
        }

        public String getOriginalTitle() {
            return originalTitle;
        }

        public void setOriginalTitle(String originalTitle) {
            this.originalTitle = originalTitle;
        }

        public String getSubtype() {
            return subtype;
        }

        public void setSubtype(String subtype) {
            this.subtype = subtype;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public ImagesBean getImages() {
            return images;
        }

        public void setImages(ImagesBean images) {
            this.images = images;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public int getAutoId() {
            return autoId;
        }

        public void setAutoId(int autoId) {
            this.autoId = autoId;
        }

        public int getSubjectId() {
            return subjectId;
        }

        public void setSubjectId(int subjectId) {
            this.subjectId = subjectId;
        }

        public List<String> getGenres() {
            return genres;
        }

        public void setGenres(List<String> genres) {
            this.genres = genres;
        }

        public List<CastsBean> getCasts() {
            return casts;
        }

        public void setCasts(List<CastsBean> casts) {
            this.casts = casts;
        }

        public List<DirectorsBean> getDirectors() {
            return directors;
        }

        public void setDirectors(List<DirectorsBean> directors) {
            this.directors = directors;
        }
    }

    public static class RatingBean {
        /**
         * max : 10
         * average : 7.2
         * stars : 40
         * min : 0
         */

        private int max;
        private double average;
        private String stars;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public double getAverage() {
            return average;
        }

        public void setAverage(double average) {
            this.average = average;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }
    }

    public static class ImagesBean {
        /**
         * small : https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2520331478.jpg
         * large : https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2520331478.jpg
         * medium : https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2520331478.jpg
         */

        private String small;
        private String large;
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }
    }

    @Entity(tableName = "tb_movie_casts", indices = {@Index(value = "movieId")},
            foreignKeys = @ForeignKey(entity = SubjectsBean.class,
                    parentColumns = "subjectId",
                    childColumns = "movieId", onDelete = ForeignKey.CASCADE))
    public static class CastsBean {
        /**
         * alt : https://movie.douban.com/celebrity/1312940/
         * avatars : {"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1503986232.61.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1503986232.61.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1503986232.61.jpg"}
         * name : 雷佳音
         * id : 1312940
         */

        private String alt;
        @Embedded
        private AvatarsBean avatars;
        private String name;
        private String id;
        @PrimaryKey(autoGenerate = true)
        private int autoId;

        private int movieId;

        public int getAutoId() {
            return autoId;
        }

        public void setAutoId(int autoId) {
            this.autoId = autoId;
        }

        public int getMovieId() {
            return movieId;
        }

        public void setMovieId(int movieId) {
            this.movieId = movieId;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public AvatarsBean getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsBean avatars) {
            this.avatars = avatars;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class AvatarsBean {
            /**
             * small : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1503986232.61.jpg
             * large : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1503986232.61.jpg
             * medium : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1503986232.61.jpg
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }
    }

    @Entity(tableName = "tb_movie_director", indices = {@Index(value = "movieId")},
            foreignKeys = @ForeignKey(entity = SubjectsBean.class,
                    parentColumns = "subjectId",
                    childColumns = "movieId", onDelete = ForeignKey.CASCADE))
    public static class DirectorsBean {
        /**
         * alt : https://movie.douban.com/celebrity/1331887/
         * avatars : {"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1520837465.56.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1520837465.56.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1520837465.56.jpg"}
         * name : 苏伦
         * id : 1331887
         */

        private String alt;
        @Embedded
        private AvatarsBeanX avatars;
        private String name;
        private int id;

        @PrimaryKey(autoGenerate = true)
        private int autoId;

        private int movieId;

        public int getAutoId() {
            return autoId;
        }

        public void setAutoId(int autoId) {
            this.autoId = autoId;
        }

        public int getMovieId() {
            return movieId;
        }

        public void setMovieId(int movieId) {
            this.movieId = movieId;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public AvatarsBeanX getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsBeanX avatars) {
            this.avatars = avatars;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public static class AvatarsBeanX {
            /**
             * small : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1520837465.56.jpg
             * large : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1520837465.56.jpg
             * medium : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1520837465.56.jpg
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }
    }
}
