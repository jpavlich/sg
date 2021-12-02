CREATE DATABASE if not exists survey;
CREATE DATABASE if not exists survey_metadata;
GRANT ALL PRIVILEGES ON survey.* TO 'survey'@'%' IDENTIFIED BY 'survey';
GRANT ALL PRIVILEGES ON survey.* TO 'survey'@'localhost' IDENTIFIED BY 'survey';
GRANT ALL PRIVILEGES ON survey_metadata.* TO 'survey'@'%' IDENTIFIED BY 'survey';
GRANT ALL PRIVILEGES ON survey_metadata.* TO 'survey'@'localhost' IDENTIFIED BY 'survey';