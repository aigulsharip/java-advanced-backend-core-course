CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       username VARCHAR(30) NOT NULL UNIQUE,
                       password VARCHAR(80) NOT NULL,
                       email VARCHAR(50) UNIQUE,
                       created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       deleted BOOLEAN NOT NULL DEFAULT false
);
ALTER TABLE users DROP CONSTRAINT users_username_key;
ALTER TABLE users DROP CONSTRAINT users_email_key;

CREATE UNIQUE INDEX ux_users_username_active
    ON users (username)
    WHERE deleted = false;

CREATE UNIQUE INDEX ux_users_email_active
    ON users (email)
    WHERE deleted = false;


INSERT INTO users (username, password, email, created, updated, deleted) VALUES ('super_admin', '$2a$10$ODYQFFe4.QGUTX37aJHfk.KIHUhdES593d1ggMRqZQF0Phk5j8hcO', 'superadmin@gmail.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, false),
                                                                                ('admin', '$2a$10$yjT3MUJ.y5vxpiWCNS4/guMcrOMEM.5F0Vo2c9pAghE2lcv6EfrAe', 'admin@gmail.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, false),
                                                                                ('user', '$2a$10$R08bWtD.BmdmJe.B3/Jwwu39oP.jIpkgaCxc2kKOz/MbrMDJ.IcjC', 'user@gmail.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, false);
