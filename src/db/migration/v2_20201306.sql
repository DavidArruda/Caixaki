CREATE OR REPLACE FUNCTION public.retira_acentos(text)
  RETURNS text AS
$BODY$
select 
translate($1,'��������������������������������������������', 
'aaaaaeeeeiiiooooouuuuAAAAAEEEEIIIOOOOOUUUUcC'); 
$BODY$
  LANGUAGE sql IMMUTABLE STRICT
  COST 100;
ALTER FUNCTION public.retira_acentos(text)
  OWNER TO postgres;
