The New TAO of Java (aka "textbook")
====================================

A textbook for CSC207, in progress.

The textbook is written in BocBook so that it can be intalled as a 
configurable Web site.

Quick Start
-----------

You can build the textbook with the following commands (more or less).

  $ cd resources
  $ make
  $ cd ..
  $ make

Building
--------
Building the textbook is potentially a multi-step process.  Sam needs to
figure out what all the steps are.  But here are a few.

* In the resources directory, copy the following files.  (You can 
  substitute your own versions of the generic files.)
        $ install generic-links.xml links.xml
        $ install generic-prefix.txt prefix.txt
        $ install generic-stylesheet.css stylesheet.css
        $ install generic-stylesheet.xsl stylesheet.xsl
        $ install generic-entities.ent entities.ent

file types
----------
Content is stored in files with a suffix of ".sect".  These are
nearly-complete docbook files, lacking only the docbook header.
(We leave out the header to make it easier to nest them within each
other and to customize the docbook.)

tools/mkwrapper adds the header to a .sect, creating a .docbook file

xsltproc is reponsible for converting from .docbook to .html.

All of this should be handled by the Makefile.

