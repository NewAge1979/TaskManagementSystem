CREATE TABLE public.comments
(
    id        BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    text      VARCHAR(1024)                           NOT NULL,
    creator   BIGINT,
    task   BIGINT,
    create_at TIMESTAMP WITHOUT TIME ZONE             NOT NULL,
    CONSTRAINT pk_comments PRIMARY KEY (id)
);

ALTER TABLE public.comments
    ADD CONSTRAINT FK_COMMENTS_ON_CREATOR FOREIGN KEY (creator) REFERENCES public.users (id)
    ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE public.comments
    ADD CONSTRAINT FK_COMMENTS_ON_TASK FOREIGN KEY (task) REFERENCES public.tasks (id)
    ON UPDATE NO ACTION ON DELETE CASCADE;