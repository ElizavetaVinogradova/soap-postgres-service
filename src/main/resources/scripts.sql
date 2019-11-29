CREATE TABLE homework."Task"
(
    "ProjectID" numeric NOT NULL DEFAULT 0,
    "ProjectVersion" integer NOT NULL DEFAULT 0,
    "InternalID" numeric NOT NULL DEFAULT 0,
    "TaskName" character varying(100),
    "InternalVersion" integer,
    "projectName" character varying(255),
    CONSTRAINT "Task_pkey" PRIMARY KEY ("ProjectID", "ProjectVersion", "InternalID")
);

CREATE TABLE homework."Project"
(
    "InternalID" numeric NOT NULL DEFAULT 0,
    "Version" integer NOT NULL DEFAULT 0,
    "ID" character varying(100),
    "Name" character varying(100),
    "Finish" timestamp without time zone,
    "InternalVersion" integer,
    CONSTRAINT "Project_pkey" PRIMARY KEY ("InternalID", "Version")
);




INSERT INTO homework."Project" ("InternalID", "Version", "ID", "Name", "Active", "InternalVersion")
VALUES (56371, 1, 17023, 'ProjectName', 1, 1 );


INSERT INTO homework."Task" ("ProjectID", "ProjectVersion", "InternalID", "TaskName", "InternalVersion", "projectName")
VALUES (56371, 1, 56372, 'TaskName', 1, 'ProjectName');