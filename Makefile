# +-----------------+-------------------------------------------------
# | Standard Targets|
# +-----------------+

default: 
	cd assignments; make default
	cd handouts; make default
	cd labs; make default
	cd misc; make default
	cd readings; make default
	cd sandbox; make default

clean:
	cd assignments; make clean
	cd handouts; make clean
	cd labs; make clean
	cd misc; make clean
	cd readings; make clean
	cd resources; make clean
	cd sandbox; make clean

# +--------------+----------------------------------------------------
# | Fun with git |
# +--------------+

# Get the status of files without worrying about the typical locally
# generated files.
status:
	git status \
	| grep -v 'html$$' \
	| grep -v 'docbook$$' \
	| grep -v 'ps$$' \
	| grep -v 'pdf$$'

# Update the repository.  (Yeah, I still think in terms of Subversion)
update:
	git pull 
