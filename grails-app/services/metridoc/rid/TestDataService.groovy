package metridoc.rid

class TestDataService {
    /**
     * For each domain object, check if any exist in the database
     * If not, populate the table with initial domain objects
     */

    def populateTestFields() {
        if (!RidLibraryUnit.first()) {
            // for Library unit
            List<String> lUnit = Arrays.asList("WIC", "HSL", "CDM", "LIPPINCOTT", "RIS",
                    "Science Libraries")
            for (String i in lUnit.sort()) {
                if (!RidLibraryUnit.findByName(i)) {
                    def gt = new RidLibraryUnit(name: i)
                    gt.save()
                    if (gt.hasErrors()) println gt.errors
                }
            }
            new RidLibraryUnit(name: "General").save()
        }
        // ---------------------------------------------------------------------------------------------
        // for department
        if (!RidDepartment.first()) {
            List<String> dps = Arrays.asList(
                    "AAMW", "ABIO", "ACCT", "ADMN", "ADMS", "AFAM", "AFRC", "AFST", "AMCS", "AMCV", "AMES",
                    "ANAT", "ANCH", "ANCS", "ANNS", "ANTH", "ARBR", "ARCH", "ARTH", "AS", "ASAM", "ASTR",
                    "BE", "BENF", "BEPP", "BIBB", "BIOC", "BIOL", "BIOM", "BIOP", "BIOT", "BLAW", "BMB", "BMP",
                    "BURS", "CAMB", "CBE", "CELL", "CENG", "CGS", "CHE", "CHEM", "CINE", "CIS", "CISE", "CIT",
                    "CIVE", "CLAR", "CLCT", "CLSD", "CLST", "CMSC", "COGS", "COLH", "COLL", "COML", "COMM",
                    "CONV", "CPLN", "CRIM", "CSEN", "DANH", "DBCH", "DCAR", "DCOH", "DEMG", "DEND", "DENT",
                    "DENV", "DMCB", "DOMD", "DORT", "DOSP", "DPED", "DPRD", "DPTH", "DRAD", "DRST", "DSCI",
                    "DYNM", "EALC", "EARC", "EAS", "ECAP", "ECON", "EDUC", "EE", "EENG", "EESC", "EMTM", "ENAS",
                    "ENGL", "ENMG", "ENVS", "EPID", "ESE", "EXEN", "FILM", "FINA", "FNAR", "FNCE", "FOLK",
                    "GAFH", "GAFL", "GAS", "GCB", "GENE", "GENH", "GEOL", "GEPH", "GRMN", "GSFA", "GSWS", "HCAD",
                    "HCMG", "HGEN", "HIST", "HSOC", "HSPV", "HSSC", "HSTP", "HUMA", "IDAT", "IMUN", "INSC",
                    "INSP", "INTL", "INTR", "INTS", "IPD", "JIO", "JWST", "LALS", "LARP", "LAUD", "LAW", "LGIC",
                    "LGST", "LIBS", "LING", "LSMP", "LTAM", "MAPP", "MATH", "MEAM", "MED", "MEDE", "MGMT", "MICB",
                    "MICR", "MKSE", "MKTG", "MMES", "MMP", "MOLB", "MSCI", "MSE", "MSEN", "MUSC", "NELC", "NETS",
                    "NGG", "NSCI", "NURS", "OBGY", "OPIM", "OPRE", "ORST", "PARA", "PATH", "PEDI", "PHIL", "PHRM",
                    "PHSO", "PHYS", "PPE", "PPMT", "PRES", "PROV", "PSCI", "PSOS", "PSYC", "PTHB", "PUBH", "PYCH",
                    "RADI", "REAL", "REGR", "RELS", "ROML", "ROMP", "RSCI", "RSMD", "SARS", "SAST", "SEAS", "SENG",
                    "SGER", "SLAV", "SOCI", "SS", "SSYS", "STAT", "STUH", "SWRK", "SYS", "SYSE", "SYST", "TCOM",
                    "THAR", "UDES", "UPRF", "URBS", "USA", "VANB", "VCSN", "VCSP", "VET", "VIPR", "VISR", "VLST",
                    "VMED", "VPTH", "VPUL", "VSUR", "WCIT", "WEM", "WH", "WHCP", "WHG", "WSTD"
            )
            List<String> dpsa = Arrays.asList(
                    "ART & ARCHEOLOGY OF MEDITERRANEAN WORLD", "ANIMAL BIOLOGY", "ACCOUNTING",
                    "ADMINISTRATIVE OFFICE - NOT SCHL RELATED", "ADMINISTRATIVE OFFICE - SCHOOL RELATED",
                    "AFRO-AMERICAN STUDIES", "AFRICANA STUDIES", "AFRICAN STUDIES PROGRAM",
                    "APPLIED MATHEMATICS & COMPUTATIONAL SCI.", "AMERICAN CIVILIZATION",
                    "ASIAN & MIDDLE EASTERN STUDIES", "ANATOMY", "ANCIENT HISTORY", "ANCIENT STUDIES",
                    "ANNENBERG SCHOOL", "ANTHROPOLOGY", "ARBORETUM", "ARCHITECTURE", "ART HISTORY",
                    "ARTS & SCIENCES", "ASIAN AMERICAN STUDIES", "ASTRONOMY", "BIOENGINEERING",
                    "BENJAMIN FRANKLIN SEMINARS", "BUSINESS ECONOMICS & PUBLIC POLICY",
                    "BIOLOGICAL BASIS OF BEHAVIOR", "BIOCHEMISTRY", "BIOLOGY", "BIOMEDICAL STUDIES",
                    "BIOPHYSICS", "BIOTECHNOLOGY", "BUSINESS LAW", "BIOCHEMISTRY & MOLECULAR BIOPHYSICS",
                    "BIOMEDICAL GRADUATE STUDIES", "BURSARS OFFICE", "CELL AND MOLECULAR BIOLOGY",
                    "CHEMICAL AND BIOMOLECULAR ENGINEERING", "CELL BIOLOGY", "CHG TO 'CHE'",
                    "COLLEGE OF LIBERAL & PROF STUDIES", "CHEMICAL ENGINEERING", "CHEMISTRY", "CINEMA STUDIES",
                    "COMPUTER AND INFORMATION SCIENCE", "CHG TO 'CIS'", "COMPUTER & INFORMATION TECHNOLOGY",
                    "CIVIL ENGINEERING (OBSOLETE)", "CLASSICAL ARCHAEOLOGY", "COLLECTIONS OFFICE",
                    "CLINICAL STUDIES PHILA (VET SCHOOL)", "CLASSICAL STUDIES",
                    "COMPARATIVE MEDICAL SCIENCES", "COGNITIVE SCIENCE", "COLLEGE FRESHMEN HOLDS", "COLLEGE",
                    "COMPARATIVE LITERATURE", "COMMUNICATIONS", "CONVERTED SUBJECTS", "CITY PLANNING",
                    "CRIMINOLOGY", "CHG TO 'CIS'", "ANATOMY/HISTOLOGY", "BIOCHEMISTRY", "DENTAl CARE SYSTEM",
                    "COMMUNITY ORAL HEALTH", "DEMOGRAPHY", "ENDODONTICS", "DENTAL", "DESIGN OF ENVIRONMENT",
                    "MICROBIOLOGY", "ORAL MEDICINE", "ORTHODONTICS", "ORAL SURGERY AND PHARMACOLOGY",
                    "PEDIATRIC DENTISTRY", "PERIODONTICS", "PATHOLOGY", "RADIOLOGY", "RESTORATIVE DENTISTRY",
                    "DECISION SCIENCES", "ORGANIZATIONAL DYNAMICS",
                    "CENTER FOR EAST ASIAN LANGUAGES AND CIVILIZATIONS", "ECOLOGICAL ARCHITECTURE",
                    "ENGINEERING & APPLIED SCIENCE", "APPLIED ECONOMICS", "ECONOMICS", "EDUCATION",
                    "ELECTRICAL ENGINEERING", "CHG TO 'EE'", "EARTH AND ENVIRONMENTAL SCIENCE",
                    "EXECUTIVE MASTERS IN TECHNOLOGY MGMT.", "CHG TO 'EAS'", "ENGLISH",
                    "ENERGY MANAGEMENT & POLICY", "ENVIRONMENTAL STUDIES", "EPIDEMIOLOGY",
                    "ELECTRICAL & SYSTEMS ENGINEERING", "EXECUTIVE ENGINEERING", "CINEMA STUDIES",
                    "CHG TO 'FNCE'", "FINE ARTS", "FINANCE", "FOLKLORE", "GOVERNMENT ADMIN/HARRISBURG",
                    "GOVERNMENT ADMINISTRATION", "GRADUATE ARTS & SCIENCES",
                    "GENOMICS AND COMPUTATIONAL BIOLOGY", "GENETICS", "GENERAL HONORS",
                    "EARTH AND ENVIRONMENTAL SCIENCE", "GRADUATE ENGINEERING HOLDS",
                    "GERMANIC LANGUAGES AND LITERATURES", "SCHOOL OF DESIGN",
                    "GENDER, SEXUALITY & WOMEN'S STUDIES", "CHG TO 'HCMG'",
                    "HEALTH CARE MANAGEMENT", "HUMAN GENETICS", "HISTORY", "HEALTH AND SOCIETIES",
                    "HISTORIC PRESERVATION", "HISTORY & SOCIOLOGY OF SCIENCE", "CHG TO 'HSPV'", "HUMANITIES",
                    "INTL DEVL & APPROPRIATE TECHNOLOGY", "IMMUNOLOGY", "INSTITUTE OF NEUROLOGICAL SCIENCES",
                    "INTERNATIONAL STUDIES", "INTERNATIONAL PROGRAMS", "INTERNATIONAL RELATIONS",
                    "INTERNATIONAL STUDIES", "INTEGRATED PRODUCT DESIGN", "JUDICIAL INQUIRY OFFICER",
                    "JEWISH STUDIES PROGRAM", "LATIN AMERICAN AND LATINO STUDIES",
                    "LANDSCAPE ARCH & REGIONAL PLANNING", "LAUDER INSTITUTE", "LAW",
                    "LOGIC, INFORMATION AND COMPUTATION", "LEGAL STUDIES AND BUSINESS ETHICS",
                    "LIBERAL STUDIES", "LINGUISTICS", "LIFE SCIENCES MANAGEMENT",
                    "LATIN AMERICAN AND LATINO STUDIES", "MASTER OF APPLIED POSITIVE PSYCHOLOGY",
                    "MATHEMATICS", "MECH ENGR AND APPLIED MECHANICS",
                    "MEDICAL", "DEPARTMENT OF MEDICAL ETHICS", "MANAGEMENT", "MICROBIOLOGY (DENTAL SCHOOL)",
                    "MICROBIOLOGY", "MARKET & SOCIAL SYSTEMS ENGINEERING", "MARKETING",
                    "MODERN MIDDLE EASTERN STUDIES", "MASTER OF MEDICAL PHYSICS", "MOLECULAR BIOLOGY",
                    "MILITARY SCIENCE", "MATERIALS SCIENCE AND ENGINEERING", "CHG TO 'MSE'", "MUSIC",
                    "NEAR EASTERN LANGUAGES AND CIVILIZATIONS", "NETWORKED AND SOCIAL SYSTEMS",
                    "NEUROSCIENCE", "NAVAL SCIENCE", "NURSING", "OB/GYN",
                    "OPERATIONS AND INFORMATION MANAGEMENT", "OPERATIONS RESERACH", "ORIENTAL STUDIES",
                    "PARASITOLOGY", "PATHOLOGY", "PEDIATRICS", "PHILOSOPHY", "PHARMACOLOGY", "PHYSIOLOGY",
                    "PHYSICS", "PHILOSOPHY, POLITICS AND ECON.", "PUBLIC POLICY & MANAGEMENT",
                    "PRESIDENT'S OFFICE", "PROVOST", "POLITICAL SCIENCE", "PENN - SUMMER OF SERVICE",
                    "PSYCHOLOGY", "PATHOBIOLOGY", "PUBLIC HEALTH STUDIES", "PSYCHIATRY", "RADIOLOGY",
                    "REAL ESTATE", "REGISTRARS OFFICE", "RELIGIOUS STUDIES", "ROMANCE LANGUAGES",
                    "ROMANCE PHILOLOGY", "REGIONAL SCIENCES", "RESEARCH MEDICINE",
                    "SOUTH ASIA REGIONAL STUDIES", "SOUTH ASIA STUDIES", "SCHOOL OF ENGINEERING",
                    "CHG TO 'SYS'", "SOCIAL GERONTOLOGY", "SLAVIC LANGUAGES", "SOCIOLOGY", "SUMMER SESSIONS",
                    "SOCIAL SYSTEMS SCIENCE", "STATISTICS", "STUDENT HEALTH INSURANCE", "SOCIAL WORK",
                    "SYSTEMS ENGINEERING", "CHG TO 'SYS'", "CHG TO 'SYS'", "TELECOMMUNICATIONS & NETWORKING",
                    "THEATRE ARTS", "URBAN DESIGN", "UNIVERSITY PROFESSORSHIP", "URBAN STUDIES",
                    "URBAN SPATIAL ANALYTICS", "ANIMAL BIOLOGY", "CLINICAL STUDIES - NBC ELECTIVES",
                    "CLINICAL STUDIES - PHILA ELECTIVES", "VETERINARY MEDICINE",
                    "PROGRAM IN ENERGY RESEARCH (VIPER)", "VET SCHOOL INDEPENDENT STUDY & RESEARCH",
                    "VISUAL STUDIES", "CSP/CSN MEDICINE COURSES", "PATHOBIOLOGY",
                    "VICE PROVOST FOR UNIVERSITY LIFE", "CSP/CSN SURGERY COURSES",
                    "WHARTON COMPUTER&INSTRUCTIONAL TCH", "WHARTON EXECUTIVE MBA PROGRAM",
                    "WHARTON UNDERGRADUATE", "WHARTON COMMUNICATION PGM", "WHARTON GRADUATE",
                    "WOMEN'S STUDIES"
            )
            // since departmental affiliation is not required, this stands for null value
            new RidDepartment(name: "", fullName: "").save(validate: false)
            for (int i = 1; i < dps.size(); i++) {
                if (!RidDepartment.findByName(dps.get(i).trim())) {
                    def da = new RidDepartment(name: dps.get(i).trim(), fullName: dpsa.get(i).trim())
                    da.save()
                    if (da.hasErrors()) println da.errors
                }
            }
        }
        if (!RidSchool.first()) {

            // ---------------------------------------------------------------------------------------------
            // for school
            List<String> schools = Arrays.asList("SAS", "SEAS", "Wharton", "GSE", "Vet", "Nursing", "Med",
                    "Dental", "SP2", "Design", "UPHS", "CHOP", "Annenberg", "Law")
            for (String i in schools.sort()) {
                if (!RidSchool.findByName(i)) {
                    def e = new RidSchool(name: i, inForm: 1)
                    e.save()
                    if (e.hasErrors()) println e.errors
                }
            }
            def outsidePleaseIndicate = "Outside Penn (please indicate)"
            def pennOtherPleaseIndicate = "Penn Other (please indicate)"
            if (!RidSchool.findByName(pennOtherPleaseIndicate)) {
                new RidSchool(name: pennOtherPleaseIndicate, inForm: 2).save()
            }

            if (!RidSchool.findByName(outsidePleaseIndicate)) {
                new RidSchool(name: outsidePleaseIndicate, inForm: 2).save()
            }
        }

        if (!RidLocation.first()) {
            // ---------------------------------------------------------------------------------------------
            // for location
            List<String> loc = Arrays.asList("Classroom", "Library conference room", "Lecture hall")
            for (String i in loc.sort()) {
                if (!RidLocation.findByName(i)) {
                    def nl = new RidLocation(name: i, inForm: 1)
                    nl.save()
                    if (nl.hasErrors()) println nl.errors
                }
            }
            def otherLocation = "Other location (please indicate)"
            if (!RidLocation.findByName(otherLocation)) {
                new RidLocation(name: otherLocation, inForm: 2).save()
            }
        }

        if (!RidInstructionalMaterials.first()) {
            // ---------------------------------------------------------------------------------------------
            // for instructionalMaterials
            List<String> im = Arrays.asList("PowerPoint", "Handout", "Quiz")
            new RidInstructionalMaterials(name: "", inForm: 1).save(validate: false)
            for (String i in im.sort()) {
                if (!RidInstructionalMaterials.findByName(i)) {
                    def nl = new RidInstructionalMaterials(name: i, inForm: 1)
                    nl.save()
                    if (nl.hasErrors()) println nl.errors
                }
            }
            def otherInstructionalMaterials = "Other instructional Materials (please indicate)"
            if (!RidInstructionalMaterials.findByName(otherInstructionalMaterials)) {
                new RidInstructionalMaterials(name: otherInstructionalMaterials, inForm: 2).save()
            }
        }

        if (!RidAudience.first()) {
            // ---------------------------------------------------------------------------------------------
            // for audience
            List<String> aud = Arrays.asList("Undergraduates", "Grad Students", "Teachers")
            new RidAudience(name: "", inForm: 1).save(validate: false)
            for (String i in aud.sort()) {
                if (!RidAudience.findByName(i)) {
                    def nl = new RidAudience(name: i, inForm: 1)
                    nl.save()
                    if (nl.hasErrors()) println nl.errors
                }
            }
            def otherAudience = "Other audience (please indicate)"
            if (!RidAudience.findByName(otherAudience)) {
                new RidAudience(name: otherAudience, inForm: 2).save()
            }
        }

        if (!RidCourseSponsor.first()) {
            // ---------------------------------------------------------------------------------------------
            // for course sponsor
            List<String> cSponsor = Arrays.asList("SAS", "SEAS", "Wharton", "GSE", "Vet", "Nursing", "Med",
                    "Dental", "SP2", "Design", "Annenberg", "Law", "Coursera",
                    "Independent Research")
            // since course sponsor is not required, this stands for null value
            new RidCourseSponsor(name: "", inForm: 1).save(validate: false)
            for (String i in cSponsor.sort()) {
                if (!RidCourseSponsor.findByName(i)) {
                    def c = new RidCourseSponsor(name: i, inForm: 1)
                    c.save()
                    if (c.hasErrors()) println c.errors
                }
            }
            def outsidePleaseIndicate = "Outside Penn (please indicate)"
            if (!RidCourseSponsor.findByName(outsidePleaseIndicate)) {
                new RidCourseSponsor(name: outsidePleaseIndicate, inForm: 2).save()
            }
        }
        if (!RidRank.first()) {

            // ---------------------------------------------------------------------------------------------
            // for rank
            List<String> ranks = Arrays.asList("Undergrad student", "Grad student", "PhD/PostDoc",
                    "Clinical: intern, resident, fellow", "Clinical: other", "Faculty", "Alumni", "Staff")
            for (String i in ranks.sort()) {
                if (!RidRank.findByName(i)) {
                    def c = new RidRank(name: i, inForm: 1)
                    c.save()
                    if (c.hasErrors()) println c.errors
                }
            }
            def otherPleaseIndicate = "Other (please indicate)"
            if (!RidRank.findByName(otherPleaseIndicate)) {
                new RidRank(name: otherPleaseIndicate, inForm: 2).save()
            }
        }

        if (!RidUserGoal.first()) {

            // ---------------------------------------------------------------------------------------------
            // for user goal -- WIC
            List<String> uGoal = Arrays.asList("Senior Thesis", "Master Thesis", "Dissertation",
                    "Independent Research", "Improvement in Teaching")
            // since here the user goal is not required, this stands for null value
            new RidUserGoal(name: "", inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("WIC")).save(validate: false)
            for (String i in uGoal.sort()) {
                if (!RidUserGoal.findByNameAndRidLibraryUnit(i, RidLibraryUnit.findByName("WIC"))) {
                    def p = new RidUserGoal(name: i, inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("WIC"))
                    p.save()
                    if (p.hasErrors()) println p.errors
                }
            }
            new RidUserGoal(name: "Other (please indicate)", inForm: 2, ridLibraryUnit: RidLibraryUnit.findByName("WIC")).save()
            // for user goal -- HSL
            uGoal = Arrays.asList("Senior Thesis", "Master Thesis", "Dissertation", "Independent Research")
            new RidUserGoal(name: "", inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("HSL")).save(validate: false)
            for (String i in uGoal.sort()) {
                if (!RidUserGoal.findByNameAndRidLibraryUnit(i, RidLibraryUnit.findByName("HSL"))) {
                    def p = new RidUserGoal(name: i, inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("HSL"))
                    p.save()
                    if (p.hasErrors()) println p.errors
                }
            }
            new RidUserGoal(name: "Other (please indicate)", inForm: 2, ridLibraryUnit: RidLibraryUnit.findByName("HSL")).save()
            // for user goal -- CDM
            uGoal = Arrays.asList("Senior Thesis", "Master Thesis", "Dissertation", "Independent Research")
            new RidUserGoal(name: "", inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("CDM")).save(validate: false)
            for (String i in uGoal) {
                if (!RidUserGoal.findByNameAndRidLibraryUnit(i, RidLibraryUnit.findByName("CDM"))) {
                    def p = new RidUserGoal(name: i, inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("CDM"))
                    p.save()
                    if (p.hasErrors()) println p.errors
                }
            }
            new RidUserGoal(name: "Other (please indicate)", inForm: 2, ridLibraryUnit: RidLibraryUnit.findByName("CDM")).save()
            // for user goal -- LIPPINCOTT
            uGoal = Arrays.asList("Senior Thesis", "Master Thesis", "Dissertation", "Independent Research")
            new RidUserGoal(name: "", inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("LIPPINCOTT")).save(validate: false)
            for (String i in uGoal.sort()) {
                if (!RidUserGoal.findByNameAndRidLibraryUnit(i, RidLibraryUnit.findByName("LIPPINCOTT"))) {
                    def p = new RidUserGoal(name: i, inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("LIPPINCOTT"))
                    p.save()
                    if (p.hasErrors()) println p.errors
                }
            }
            new RidUserGoal(name: "Other (please indicate)", inForm: 2, ridLibraryUnit: RidLibraryUnit.findByName("LIPPINCOTT")).save()
            // for user goal -- RIS
            uGoal = Arrays.asList("Research Paper", "Course Project", "Senior Thesis", "Master Thesis",
                    "Dissertation", "Research article", "Monograph", "Data Management",
                    "Independent Research", "Course Creation", "Grant Proposal")
            new RidUserGoal(name: "", inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("RIS")).save(validate: false)
            for (String i in uGoal) {
                if (!RidUserGoal.findByNameAndRidLibraryUnit(i, RidLibraryUnit.findByName("RIS"))) {
                    def p = new RidUserGoal(name: i, inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("RIS"))
                    p.save()
                    if (p.hasErrors()) println p.errors
                }
            }
            new RidUserGoal(name: "Other (please indicate)", inForm: 2, ridLibraryUnit: RidLibraryUnit.findByName("RIS")).save()
            // for user goal -- Science Libraries
            uGoal = Arrays.asList("Research Paper", "Course Project", "Senior Thesis", "Master Thesis",
                    "Dissertation", "Research article", "Monograph", "Data Management",
                    "Independent Research", "Course Creation", "Grant Proposal")
            new RidUserGoal(name: "", inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("Science Libraries")).save(validate: false)
            for (String i in uGoal) {
                if (!RidUserGoal.findByNameAndRidLibraryUnit(i, RidLibraryUnit.findByName("Science Libraries"))) {
                    def p = new RidUserGoal(name: i, inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("Science Libraries"))
                    p.save()
                    if (p.hasErrors()) println p.errors
                }
            }
            new RidUserGoal(name: "Other (please indicate)", inForm: 2, ridLibraryUnit: RidLibraryUnit.findByName("Science Libraries")).save()
            // for user goal -- General
            uGoal = Arrays.asList("Research Paper", "Course Project", "Senior Thesis", "Master Thesis",
                    "Dissertation", "Research article", "Monograph", "Data Management",
                    "Independent Research", "Course Creation", "Grant Proposal")
            new RidUserGoal(name: "", inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("General")).save(validate: false)
            for (String i in uGoal) {
                if (!RidUserGoal.findByNameAndRidLibraryUnit(i, RidLibraryUnit.findByName("General"))) {
                    def p = new RidUserGoal(name: i, inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("General"))
                    p.save()
                    if (p.hasErrors()) println p.errors
                }
            }
            new RidUserGoal(name: "Other (please indicate)", inForm: 2, ridLibraryUnit: RidLibraryUnit.findByName("General")).save()
        }

        if (!RidModeOfConsultation.first()) {
            // ---------------------------------------------------------------------------------------------
            // for mode of consultation -- WIC
            List<String> cMode = Arrays.asList("Email", "Phone", "Chat", "Conferencing software",
                    "Video or web conference", "In person (in library)", "In person (outside library)")
            new RidModeOfConsultation(name: "", inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("WIC")).save(validate: false)
            for (String i in cMode) {
                if (!RidModeOfConsultation.findByNameAndRidLibraryUnit(i, RidLibraryUnit.findByName("WIC"))) {
                    def c = new RidModeOfConsultation(name: i, inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("WIC"))
                    c.save()
                    if (c.hasErrors()) println c.errors
                }
            }
            new RidModeOfConsultation(name: "Other (please indicate)", inForm: 2, ridLibraryUnit: RidLibraryUnit.findByName("WIC")).save()
            // for mode of consutlation -- HSL
            cMode = Arrays.asList("Email", "Phone", "Chat", "Conferencing software",
                    "In person (in library)", "In person (outside library)")
            new RidModeOfConsultation(name: "", inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("HSL")).save(validate: false)
            for (String i in cMode) {
                if (!RidModeOfConsultation.findByNameAndRidLibraryUnit(i, RidLibraryUnit.findByName("HSL"))) {
                    def c = new RidModeOfConsultation(name: i, inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("HSL"))
                    c.save()
                    if (c.hasErrors()) println c.errors
                }
            }
            // for mode of consutlation -- CDM
            cMode = Arrays.asList("Email", "Phone", "Chat", "Conferencing software",
                    "In person (in library)", "In person (outside library)")
            new RidModeOfConsultation(name: "", inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("CDM")).save(validate: false)
            for (String i in cMode) {
                if (!RidModeOfConsultation.findByNameAndRidLibraryUnit(i, RidLibraryUnit.findByName("CDM"))) {
                    def c = new RidModeOfConsultation(name: i, inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("CDM"))
                    c.save()
                    if (c.hasErrors()) println c.errors
                }
            }
            // for mode of consutlation -- LIPPINCOTT
            cMode = Arrays.asList("Email", "Phone", "Chat", "Conferencing software",
                    "In person (in library)", "In person (outside library)")
            new RidModeOfConsultation(name: "", inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("LIPPINCOTT")).save(validate: false)
            for (String i in cMode) {
                if (!RidModeOfConsultation.findByNameAndRidLibraryUnit(i, RidLibraryUnit.findByName("LIPPINCOTT"))) {
                    def c = new RidModeOfConsultation(name: i, inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("LIPPINCOTT"))
                    c.save()
                    if (c.hasErrors()) println c.errors
                }
            }
            // for mode of consutlation -- RIS
            cMode = Arrays.asList("Email", "Phone", "Chat", "Conferencing software",
                    "In person (in library)", "In person (outside library)")
            new RidModeOfConsultation(name: "", inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("RIS")).save(validate: false)
            for (String i in cMode) {
                if (!RidModeOfConsultation.findByNameAndRidLibraryUnit(i, RidLibraryUnit.findByName("RIS"))) {
                    def c = new RidModeOfConsultation(name: i, inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("RIS"))
                    c.save()
                    if (c.hasErrors()) println c.errors
                }
            }
            // for mode of consutlation -- Science Libraries
            cMode = Arrays.asList("Email", "Phone", "Chat", "Conferencing software",
                    "In person (in library)", "In person (outside library)")
            new RidModeOfConsultation(name: "", inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("Science Libraries")).save(validate: false)
            for (String i in cMode) {
                if (!RidModeOfConsultation.findByNameAndRidLibraryUnit(i, RidLibraryUnit.findByName("Science Libraries"))) {
                    def c = new RidModeOfConsultation(name: i, inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("Science Libraries"))
                    c.save()
                    if (c.hasErrors()) println c.errors
                }
            }
            // for mode of consutlation -- General
            cMode = Arrays.asList("Email", "Phone", "Chat", "Conferencing software",
                    "In person (in library)", "In person (outside library)")
            new RidModeOfConsultation(name: "", inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("General")).save(validate: false)
            for (String i in cMode) {
                if (!RidModeOfConsultation.findByNameAndRidLibraryUnit(i, RidLibraryUnit.findByName("General"))) {
                    def c = new RidModeOfConsultation(name: i, inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("General"))
                    c.save()
                    if (c.hasErrors()) println c.errors
                }
            }
        }

        if (!RidSessionType.first()) {

            // ---------------------------------------------------------------------------------------------
            // for session type -- WIC
            List<String> cType = Arrays.asList("Email", "Phone", "Chat", "Conferencing software",
                    "Video or web conference", "In person (in library)", "In person (outside library)")
            new RidSessionType(name: "", inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("WIC")).save(validate: false)
            for (String i in cType) {
                if (!RidSessionType.findByNameAndRidLibraryUnit(i, RidLibraryUnit.findByName("WIC"))) {
                    def c = new RidSessionType(name: i, inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("WIC"))
                    c.save()
                    if (c.hasErrors()) println c.errors
                }
            }
            new RidSessionType(name: "Other (please indicate)", inForm: 2, ridLibraryUnit: RidLibraryUnit.findByName("WIC")).save()
            // for session type -- HSL
            cType = Arrays.asList("Email", "Phone", "Chat", "Conferencing software",
                    "In person (in library)", "In person (outside library)")
            new RidSessionType(name: "", inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("HSL")).save(validate: false)
            for (String i in cType) {
                if (!RidSessionType.findByNameAndRidLibraryUnit(i, RidLibraryUnit.findByName("HSL"))) {
                    def c = new RidSessionType(name: i, inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("HSL"))
                    c.save()
                    if (c.hasErrors()) println c.errors
                }
            }
            // for session type -- CDM
            cType = Arrays.asList("Email", "Phone", "Chat", "Conferencing software",
                    "In person (in library)", "In person (outside library)")
            new RidSessionType(name: "", inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("CDM")).save(validate: false)
            for (String i in cType) {
                if (!RidSessionType.findByNameAndRidLibraryUnit(i, RidLibraryUnit.findByName("CDM"))) {
                    def c = new RidSessionType(name: i, inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("CDM"))
                    c.save()
                    if (c.hasErrors()) println c.errors
                }
            }
            // for session type -- LIPPINCOTT
            cType = Arrays.asList("Email", "Phone", "Chat", "Conferencing software",
                    "In person (in library)", "In person (outside library)")
            new RidSessionType(name: "", inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("LIPPINCOTT")).save(validate: false)
            for (String i in cType) {
                if (!RidSessionType.findByNameAndRidLibraryUnit(i, RidLibraryUnit.findByName("LIPPINCOTT"))) {
                    def c = new RidSessionType(name: i, inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("LIPPINCOTT"))
                    c.save()
                    if (c.hasErrors()) println c.errors
                }
            }
            // for session type -- RIS
            cType = Arrays.asList("Email", "Phone", "Chat", "Conferencing software",
                    "In person (in library)", "In person (outside library)")
            new RidSessionType(name: "", inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("RIS")).save(validate: false)
            for (String i in cType) {
                if (!RidSessionType.findByNameAndRidLibraryUnit(i, RidLibraryUnit.findByName("RIS"))) {
                    def c = new RidSessionType(name: i, inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("RIS"))
                    c.save()
                    if (c.hasErrors()) println c.errors
                }
            }
            // for session type -- Science Libraries
            cType = Arrays.asList("Email", "Phone", "Chat", "Conferencing software",
                    "In person (in library)", "In person (outside library)")
            new RidSessionType(name: "", inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("Science Libraries")).save(validate: false)
            for (String i in cType) {
                if (!RidSessionType.findByNameAndRidLibraryUnit(i, RidLibraryUnit.findByName("Science Libraries"))) {
                    def c = new RidSessionType(name: i, inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("Science Libraries"))
                    c.save()
                    if (c.hasErrors()) println c.errors
                }
            }
            // for session type -- General
            cType = Arrays.asList("Email", "Phone", "Chat", "Conferencing software",
                    "In person (in library)", "In person (outside library)")
            new RidSessionType(name: "", inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("General")).save(validate: false)
            for (String i in cType) {
                if (!RidSessionType.findByNameAndRidLibraryUnit(i, RidLibraryUnit.findByName("General"))) {
                    def c = new RidSessionType(name: i, inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("General"))
                    c.save()
                    if (c.hasErrors()) println c.errors
                }
            }
        }

        if (!RidServiceProvided.first()) {
            // ---------------------------------------------------------------------------------------------
            // for service provided -- WIC
            List<String> sProvided = Arrays.asList("Course design", "Research assistance",
                    "Instructional support (apart from course design)", "Tour",
                    "Tech/Software instruction", "Mobile technology", "Assistance to undergraduates")
            new RidServiceProvided(name: "", inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("WIC")).save(validate: false)
            for (String i in sProvided) {
                if (!RidServiceProvided.findByNameAndRidLibraryUnit(i, RidLibraryUnit.findByName("WIC"))) {
                    def s = new RidServiceProvided(name: i, inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("WIC"))
                    s.save()
                    if (s.hasErrors()) println s.errors
                }
            }
            new RidServiceProvided(name: "Other (please indicate)", inForm: 2, ridLibraryUnit: RidLibraryUnit.findByName("WIC")).save()
            // for service provided -- HSL
            sProvided = Arrays.asList("Research assistance", "Acquisitions/Collections",
                    "Copyright assistance for author", "Copyright assistance for rank",
                    "Correct an operational or service breakdown (incl. tech support)",
                    "Instructional support (apart from course design, including courseware)",
                    "Clinical decision making", "Tech/Software instruction", "Search instruction",
                    "Literature search", "Mobile technology", "Bibliometrics or citation metrics",
                    "Consumer health", "Admin/policy questions", "Citation management instruction",
                    "Scholarly Commons/Repository Services", "Creating faculty profiles/selected works/VIVO",
                    "Comprehensive Lit Search/Systematic Reviews")
            new RidServiceProvided(name: "", inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("HSL")).save(validate: false)
            for (String i in sProvided) {
                if (!RidServiceProvided.findByNameAndRidLibraryUnit(i, RidLibraryUnit.findByName("HSL"))) {
                    def s = new RidServiceProvided(name: i, inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("HSL"))
                    s.save()
                    if (s.hasErrors()) println s.errors
                }
            }
            new RidServiceProvided(name: "Other (please indicate)", inForm: 2, ridLibraryUnit: RidLibraryUnit.findByName("HSL")).save()
            // for service provided -- CDM
            sProvided = Arrays.asList("Course design", "Research assistance",
                    "Acquisitions/Collections", "Copyright assistance for author", "Copyright assistance for rank",
                    "Correct an operational or service breakdown (incl. tech support)",
                    "Instructional support (apart from course design)", "Clinic", "Tour",
                    "Tech/Software instruction", "Search instruction",
                    "Literature search", "Mobile technology", "Bibliometrics or citation metrics",
                    "Admin/policy questions", "Citation management instruction",
                    "Scholarly Commons/Repository Services", "Creating faculty profiles/selected works/VIVO",
                    "Coursera/MOOCs support", "Research practice support")
            new RidServiceProvided(name: "", inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("CDM")).save(validate: false)
            for (String i in sProvided) {
                if (!RidServiceProvided.findByNameAndRidLibraryUnit(i, RidLibraryUnit.findByName("CDM"))) {
                    def s = new RidServiceProvided(name: i, inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("CDM"))
                    s.save()
                    if (s.hasErrors()) println s.errors
                }
            }
            new RidServiceProvided(name: "Other (please indicate)", inForm: 2, ridLibraryUnit: RidLibraryUnit.findByName("CDM")).save()
            // for service provided -- LIPPINCOTT
            sProvided = Arrays.asList("Research assistance", "Acquisitions/Collections",
                    "Correct an operational or service breakdown (incl. tech support)",
                    "Instructional support (apart from course design)", "Tour",
                    "Tech/Software instruction", "Search instruction",
                    "Literature search", "Bibliometrics or citation metrics",
                    "Admin/policy questions", "Citation management instruction")
            new RidServiceProvided(name: "", inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("LIPPINCOTT")).save(validate: false)
            for (String i in sProvided) {
                if (!RidServiceProvided.findByNameAndRidLibraryUnit(i, RidLibraryUnit.findByName("LIPPINCOTT"))) {
                    def s = new RidServiceProvided(name: i, inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("LIPPINCOTT"))
                    s.save()
                    if (s.hasErrors()) println s.errors
                }
            }
            new RidServiceProvided(name: "Other (please indicate)", inForm: 2, ridLibraryUnit: RidLibraryUnit.findByName("LIPPINCOTT")).save()
            // for service provided -- RIS
            sProvided = Arrays.asList("Research assistance", "Clinic", "Tour",
                    "Acquisitions/Collections", "Citation management instruction", "Instructional Support",
                    "Tech/Software instruction", "Mobile technology", "Correct an Operational or Service Breakdown",
                    "Admin/policy questions", "Bibliometrics or citation metrics", "Copyright",
                    "Scholarly Commons/Repository Services")
            new RidServiceProvided(name: "", inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("RIS")).save(validate: false)
            for (String i in sProvided) {
                if (!RidServiceProvided.findByNameAndRidLibraryUnit(i, RidLibraryUnit.findByName("RIS"))) {
                    def s = new RidServiceProvided(name: i, inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("RIS"))
                    s.save()
                    if (s.hasErrors()) println s.errors
                }
            }
            new RidServiceProvided(name: "Other (please indicate)", inForm: 2, ridLibraryUnit: RidLibraryUnit.findByName("RIS")).save()
            // for service provided -- Science Libraries
            sProvided = Arrays.asList("Research assistance", "Clinic", "Tour",
                    "Acquisitions/Collections", "Citation management instruction", "Instructional Support",
                    "Tech/Software instruction", "Mobile technology", "Correct an Operational or Service Breakdown",
                    "Admin/policy questions", "Bibliometrics or citation metrics", "Copyright",
                    "Scholarly Commons/Repository Services")
            new RidServiceProvided(name: "", inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("Science Libraries")).save(validate: false)
            for (String i in sProvided) {
                if (!RidServiceProvided.findByNameAndRidLibraryUnit(i, RidLibraryUnit.findByName("Science Libraries"))) {
                    def s = new RidServiceProvided(name: i, inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("Science Libraries"))
                    s.save()
                    if (s.hasErrors()) println s.errors
                }
            }
            new RidServiceProvided(name: "Other (please indicate)", inForm: 2, ridLibraryUnit: RidLibraryUnit.findByName("Science Libraries")).save()
            // for service provided -- General
            sProvided = Arrays.asList("Research assistance", "Clinic", "Tour",
                    "Acquisitions/Collections", "Citation management instruction", "Instructional Support",
                    "Tech/Software instruction", "Mobile technology", "Correct an Operational or Service Breakdown",
                    "Admin/policy questions", "Bibliometrics or citation metrics", "Copyright",
                    "Scholarly Commons/Repository Services")
            new RidServiceProvided(name: "", inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("General")).save(validate: false)
            for (String i in sProvided) {
                if (!RidServiceProvided.findByNameAndRidLibraryUnit(i, RidLibraryUnit.findByName("General"))) {
                    def s = new RidServiceProvided(name: i, inForm: 1, ridLibraryUnit: RidLibraryUnit.findByName("General"))
                    s.save()
                    if (s.hasErrors()) println s.errors
                }
            }
            new RidServiceProvided(name: "Other (please indicate)", inForm: 2, ridLibraryUnit: RidLibraryUnit.findByName("General")).save()
        }
    }
}
