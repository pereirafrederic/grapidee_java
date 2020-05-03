
CREATE TABLE asso_grappe (
  id bigint(20) NOT NULL,
  idee_id bigint(20) NOT NULL,
  grappe_id bigint(20) NOT NULL,
  type_id bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE asso_idee (
  id bigint(20) NOT NULL,
  liaison varchar(100) COLLATE utf8_bin DEFAULT NULL,
  maitre_id bigint(20) NOT NULL,
  esclave_id bigint(20) NOT NULL,
  grappe_id bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE grappe (
  id bigint(20) NOT NULL,
  nom varchar(100) COLLATE utf8_bin NOT NULL,
  description varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  grappe_id bigint(20) DEFAULT NULL,
  type_id bigint(11) DEFAULT NULL,
  ordre int(11) DEFAULT '99'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE idee (
  id bigint(20) NOT NULL,
  nom varchar(100) COLLATE utf8_bin NOT NULL,
  description longtext COLLATE utf8_bin
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE type_grappe (
  id bigint(20) NOT NULL,
  nom varchar(100) COLLATE utf8_bin NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

ALTER TABLE asso_grappe
  ADD PRIMARY KEY (id);

ALTER TABLE asso_idee
  ADD PRIMARY KEY (id);

ALTER TABLE grappe
  ADD PRIMARY KEY (id);

ALTER TABLE idee
  ADD PRIMARY KEY (id);

ALTER TABLE type_grappe
  ADD PRIMARY KEY (id);

ALTER TABLE asso_grappe
  MODIFY id bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=472;

ALTER TABLE asso_idee
  MODIFY id bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=515;

ALTER TABLE grappe
  MODIFY id bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=684;

ALTER TABLE idee
  MODIFY id bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1756;

ALTER TABLE type_grappe
  MODIFY id bigint(20) NOT NULL AUTO_INCREMENT;
COMMIT;
