package arief.com.celltype.model;

import java.util.List;

/**
 * All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Originally written by Author Name Arief Maffrudin A N, 13/04/17
 */

public class Item {

    public String cell_type;
    public int company_id;
    public String type;
    public String name;
    public String logo_path;
    public String web_site;
    public int industry_id;
    public String industry_name;
    public String simple_desc;
    public float rate_total_avg;
    public String ranking;
    public String review_summary;
    public int salary_avg;
    public String interview_question;
    public float interview_difficulty;
    public boolean has_job_posting;

    public int count;
    public List<Theme> themes;

    public int id;
    public String title;
    public String company_name;
    public String logo;
    public float review_avg_cache;


    public String occupation_name;
    public String date;
    public int days_ago;
    public String pros;
    public String cons;


    public int signed_in;

    public String salary_ranking;
    public int salary_highest;
    public int salary_lowest;

    public class Theme{

        public int id;
        public String title;
        public String color;
        public String cover_image;


    }

}
