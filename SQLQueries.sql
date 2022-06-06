SELECT * FROM Country WHERE CountryID IN (SELECT CountryID FROM City GROUP BY CountryID HAVING SUM(Population) > 400);

SELECT DISTINCT Country.Name
FROM Country
LEFT JOIN City
ON Country.CountryID=City.CountryID
LEFT JOIN Building
ON City.CityId=Building.CityID
WHERE Building.BuildingID IS NULL;
