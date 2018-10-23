create table job_category
	(cate_code varchar(15) not null,
	 title varchar(200) not null,
	 pay_range_high int,
	 pay_range_low int check (pay_range_low >20000),
	 parent_cate varchar(200),
	 primary key(cate_code)
);

create table skill_clusters
	(skill_code varchar(20),
	skill_title varchar(250),
	skill_parent varchar(20),
	 primary key(skill_code)
	);

create table knowledge_skills
	(ks_code varchar(10),
	 title varchar(200),
	 description varchar(500),
	 lvl varchar(15),
	 primary key(ks_code)
	);


create table sector_classification
	(sec_id varchar(10),
	 title varchar(200),
 	 parent_sec varchar(10),
	 primary key(sec_id)
	 );

create table company
	(comp_id int,
	 comp_name varchar(80),
	 street_number int,
	 street_name varchar(200),
	 apt_number varchar(10),
	 city varchar(200),
	 state varchar(200),
	 zip int,
	 sec_id varchar(10),
	 website varchar(50),
	 primary key(comp_id),
	 foreign key(sec_id) references sector_classification(sec_id)
	);

create table person
	(per_id int,
	 first_name varchar(200),
	 middle_name varchar(200),
 	 last_name varchar(200),
	 street_number int,
	 street_name varchar(200),
	 apt_number varchar(10),
 	 city varchar(200),
	 state varchar(20),
	 email varchar(40),
	 gender varchar (10),
	 primary key(per_id)
	 );

create table course
	(c_code varchar(20),
	 title varchar(200),
	 lvl varchar(20),
	 description varchar(80),
	 status varchar(40),
	 retail_price numeric(6,2),
	 primary key(c_code)
	 );

create table certificates
	( cert_id varchar(10),
	cert_title varchar(200),
	description varchar(500),
	primary key(cert_id)
	);

create table teaches
	(ks_code varchar(10),
	 c_code varchar(20),
	 primary key(ks_code, c_code),
	 foreign key(ks_code) references knowledge_skills(ks_code),
	 foreign key(c_code) references course(c_code)
	);

create table has_skill
	(per_id int,
	 ks_code varchar(10),
	 primary key(per_id,ks_code),
	 foreign key(per_id) references person(per_id),
	 foreign key(ks_code) references knowledge_skills(ks_code)
);

create table comp_speciality
	(comp_id int,
	 name varchar(20),
	 primary key(comp_id, name),
	 foreign key(comp_id) references company(comp_id)
	);

create table comp_sectors
	(comp_id int,
	 sec_id varchar(10),
	 primary key(comp_id, sec_id),
	 foreign key(comp_id) references company(comp_id),
	 foreign key(sec_id) references sector_classification(sec_id)
	 );

create table person_phone
	(per_id int,
	 phone_num varchar(20),
	 primary key(per_id, phone_num),
	 foreign key(per_id) references person(per_id)
	 );

create table section
	(sec_no varchar(10),
	 c_code varchar(20),
	 complete_date date,
	 offered_by varchar(40),
	 format varchar(20),
     price numeric(6,2),
	 primary key(sec_no, c_code, offered_by, complete_date),
	 foreign key(c_code) references course(c_code)
	 );

create table takes
	(per_id int,
	 sec_no varchar(10),
	 complete_date date,
	 offered_by varchar(20),
	 c_code varchar(20),
	 primary key(per_id,  sec_no, offered_by, c_code),
	 foreign key(per_id) references person(per_id),
	foreign key(sec_no, c_code, offered_by, complete_date) references section(sec_no, c_code, offered_by, complete_date)
 );

 create table job_position
	(pos_code varchar(10),
	 pos_title varchar(30),
	 emp_mode varchar(20),
	 pay_rate  numeric(10,2),
	 pay_type varchar(10),
	 cate_code varchar(15),
	 comp_id int,
	 primary key(pos_code),
	 foreign key(cate_code) references job_category(cate_code),
	 foreign key(comp_id) references company(comp_id)
	);

create table works
	(per_id int,
	 pos_code varchar(10),
	 start_date date,
	 end_date date,
	 primary key(per_id, pos_code),
	 foreign key(per_id) references person(per_id),
	 foreign key(pos_code) references job_position(pos_code)
	 );

create table cert_course
	(c_code varchar(20),
	cert_id	varchar(10),
	primary key(c_code, cert_id),
	foreign key(c_code) references course(c_code),
	foreign key(cert_id) references certificates(cert_id)
	);

create table prereqs
	(c_code varchar(20),
	prereq_code varchar(20),
	primary key(c_code, prereq_code),
	foreign key(c_code) references course(c_code)
		on delete cascade,
	foreign key(prereq_code) references course(c_code)
	);

create table requirements
	(ks_code varchar(10),
	 pos_code varchar(10),
	 primary key(ks_code, pos_code),
	 foreign key(ks_code) references knowledge_skills(ks_code),
 	 foreign key(pos_code) references job_position(pos_code)
	);


create table skills_category
	(skill_code varchar(20),
	 ks_code varchar(10),
	 primary key(skill_code, ks_code),
	 foreign key(skill_code) references skill_clusters(skill_code),
	 foreign key(ks_code) references knowledge_skills(ks_code)
	);

create table core_skills
	(cate_code varchar(15),
	 skill_code varchar(20),
	 primary key(cate_code,skill_code),
	 foreign key(cate_code) references job_category(cate_code),
	 foreign key(skill_code) references skill_clusters(skill_code)
	);
