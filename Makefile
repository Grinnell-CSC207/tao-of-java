default: assignments labs readings

assignments: assignments/*
	$(MAKE) -C assignments

labs: labs/*
	$(MAKE) -C labs

readings: readings/*
	$(MAKE) -C readings

update:
	svn update

# Get the status of files without worrying about the typical locally
# generated files.
status:
	git status \
	| grep -v 'html$$' \
	| grep -v 'docbook$$' \
	| grep -v 'ps$$' \
	| grep -v 'pdf$$'

