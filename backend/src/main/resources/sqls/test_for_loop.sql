DECLARE @idList TABLE (id INT);
INSERT INTO @idList (id) SELECT ID FROM ACCOUNT ;

DECLARE @id INT;
WHILE (SELECT COUNT(*) FROM @idList) > 0
BEGIN
    SELECT TOP 1 @id = id FROM @idList;
    
    INSERT INTO Test_PERMISSION (accId, roleId)
    VALUES (@id, 19);
   INSERT INTO Test_PERMISSION (accId, roleId)
    VALUES (@id, 21);

    DELETE FROM @idList WHERE id = @id;
END;
