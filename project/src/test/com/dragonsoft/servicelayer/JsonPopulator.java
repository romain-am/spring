package test.com.dragonsoft.servicelayer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.com.dragonsoft.clients.ClientInfo;
import main.com.dragonsoft.clients.Department;
import main.com.dragonsoft.clients.Team;
import main.com.dragonsoft.credentials.Authority;
import main.com.dragonsoft.credentials.AuthorityType;
import main.com.dragonsoft.credentials.User;
import main.com.dragonsoft.credentials.UserAuthority;
import main.com.dragonsoft.credentials.UserRepository;
import main.com.dragonsoft.users.UserCV;
import main.com.dragonsoft.users.UserInfo;

@Component
public class JsonPopulator {
	/*
	 * Creates the Json files for populating the repository
	 * 
	 */

	@Autowired
	UserRepository userRepo;

	private static final String EMAILEND = "@test.fr";
	private static final String PASSWORD = "test_password";
	private static final List<String> citiesList = new ArrayList<>(
			Arrays.asList(
					"Armentieres",
					"Arras",
					"Bethune",
					"Boulogne",
					"Calais",
					"Cambrai",
					"Croix",
					"Douai",
					"Dunkirk",
					"Gravelines",
					"Henin-Beaumont",
					"Hesdin",
					"Le Touquet-Paris-Plage",
					"Lens",
					"Lievin",
					"Lille",
					"Marcq-en-Baroeul",
					"Maubeuge",
					"Roubaix",
					"Saint-Amand-les-Eaux",
					"Saint-Omer",
					"Tourcoing",
					"Valenciennes",
					"Wattrelos",
					"Bar-le-Duc",
					"Domremy-la-Pucelle",
					"Epinal",
					"Forbach",
					"Longwy",
					"Lunéville",
					"Metz",
					"Nancy",
					"Remiremont",
					"Saint-Die",
					"Saint-Mihiel",
					"Thionville",
					"Toul",
					"Verdun",
					"Argenteuil",
					"Athis-Mons",
					"Champigny-sur-Marne",
					"Charenton-le-Pont",
					"Chatillon",
					"Chatou",
					"Chelles",
					"Clichy",
					"Colombes",
					"Corbeil-Essonnes",
					"Courbevoie",
					"Creteil",
					"Drancy",
					"Epinay-sur-Seine",
					"Etampes",
					"Evry",
					"Fontainebleau",
					"Fresnes",
					"Gagny",
					"Gennevilliers",
					"Issy-les-Moulineaux",
					"Ivry-sur-Seine",
					"Levallois-Perret",
					"Malakoff",
					"Meaux",
					"Melun",
					"Meudon",
					"Montreuil",
					"Montrouge",
					"Nanterre",
					"Nemours",
					"Neuilly-sur-Seine",
					"Paris",
					"Poissy",
					"Pontoise",
					"Provins",
					"Puteaux",
					"Rambouillet",
					"Rueil-Malmaison",
					"Saint-Cloud",
					"Saint-Denis",
					"Saint-Germain-en-Laye",
					"Saint-Maur-des-Fosses",
					"Saint-Ouen",
					"Senart",
					"Sevres",
					"Suresnes",
					"Versailles",
					"Villejuif",
					"Villeneuve-Saint-Georges",
					"Vincennes",
					"Viry-Chatillon",
					"Vitry-sur-Seine",
					"New York,",
					"Los Angeles",
					"Chicago",
					"Houston",
					"Phoenix",
					"Philadelphia",
					"San Antonio",
					"San Diego",
					"Dallas",
					"San Jose",
					"Austin",
					"Jacksonville",
					"Fort Worth",
					"Columbus",
					"Charlotte",
					"San Francisco ",
					"Indianapolis",
					"Seattle",
					"Denver",
					"Washington",
					"Boston",
					"El Paso",
					"Nashville",
					"Detroit",
					"Oklahoma City ",
					"Portland"));

	private static final List<String> frameworksList = new ArrayList<>(
			Arrays.asList(
					"Bottle",
					"CherryPy",
					"Django",
					"Flask",
					"Grok",
					"Karrigell",
					"Pylons",
					"Pyramid",
					"TurboGears",
					"Tornado",
					"Twisted",
					"Web2py",
					"Jersey",
					"Spark",
					"Apache Hadoop",
					"Hibernate",
					"Struts",
					"Spring",
					"React.js",
					"Angular",
					"Bootstrap",
					"Dojo Widgets",
					"Ext JS",
					"Foundation",
					"jQuery UI",
					"jQWidgets",
					"JSF",
					"OpenUI5",
					"Polymer",
					"qooxdoo",
					"SmartClient",
					"Vue.js",
					"Webix",
					"WinJS",
					"Svelte"));
	private static final List<String> databaseList = new ArrayList<>(
			Arrays.asList("4th Dimension",
					"Adabas D",
					"Alpha Five",
					"Apache Derby",
					"Aster Data",
					"Amazon Aurora",
					"Altibase",
					"CA Datacom",
					"CA IDMS",
					"Clarion",
					"ClickHouse",
					"Clustrix",
					"CockroachDB",
					"CSQL",
					"CUBRID",
					"DataEase",
					"DataFlex",
					"Database Management Library",
					"Dataphor",
					"dBase",
					"Derby",
					"Empress Embedded Database",
					"Exasol",
					"EnterpriseDB",
					"eXtremeDB",
					"FileMaker Pro",
					"Firebird",
					"FrontBase",
					"Google Fusion Tables",
					"Greenplum",
					"GroveSite",
					"H2",
					"Helix database",
					"HSQLDB",
					"IBM Business System 12",
					"IBM DB2",
					"IBM Lotus Approach",
					"IBM DB2 Express-C",
					"Infobright",
					"Informix",
					"Ingres",
					"InterBase",
					"InterSystems Cache",
					"InterSystems IRIS Data Platform",
					"LibreOffice Base",
					"Linter",
					"MariaDB",
					"MaxDB",
					"MemSQL",
					"Microsoft Access",
					"Microsoft Jet Database Engine",
					"Microsoft SQL Server",
					"Microsoft SQL Server Express",
					"SQL Azure",
					"Microsoft Visual FoxPro",
					"Mimer SQL",
					"MonetDB",
					"mSQL",
					"MySQL",
					"Netezza",
					"NexusDB",
					"NonStop SQL",
					"NuoDB",
					"Omnis Studio",
					"OpenLink Virtuoso",
					"OpenLink Virtuoso Universal Server",
					"OpenOffice.org Base",
					"Oracle",
					"Oracle Rdb for OpenVMS",
					"Panorama",
					"Paradox",
					"Percona Server for MySQL",
					"Percona XtraDB Cluster",
					"Pervasive PSQL",
					"Polyhedra",
					"PostgreSQL",
					"Postgres Plus Advanced Server",
					"Progress Software",
					"Raima Database Manager (RDM)",
					"RDM Server",
					"R:Base",
					"RethinkDB",
					"SAND CDBMS",
					"SAP HANA",
					"SAP Adaptive Server Enterprise",
					"SAP IQ",
					"Snowflake Cloud Data Warehouse",
					"solidDB",
					"SQL Anywhere",
					"SQLBase",
					"SQLite",
					"SQream DB",
					"SAP Advantage Database Server",
					"Teradata",
					"Tibero",
					"TiDB",
					"TimesTen",
					"Trafodion",
					"Unisys RDMS 2200",
					"UniData",
					"UniVerse",
					"Vectorwise",
					"Vertica",
					"VoltDB"));

	private static final List<String> languageList = new ArrayList<>(
			Arrays.asList("Java",
					"C",
					"Python",
					"C++",
					"C++ ",
					"Visual Basic .NET",
					"JavaScript",
					"C#",
					"PHP",
					"SQL",
					"Objective-C",
					"Assembly language",
					"MATLAB",
					"Perl",
					"Object Pascal",
					"R",
					"Ruby",
					"Visual Basic",
					"Go",
					"Groovy",
					"Swift",
					"PL/SQL",
					"SAS",
					"D",
					"Lua",
					"Dart",
					"Fortran",
					"COBOL",
					"Scratch",
					"Scala",
					"ABAP",
					"Lisp",
					"Logo",
					"Ada",
					"Transact-SQL",
					"Prolog",
					"Scheme",
					"Rust"));
	private static final List<String> osList = new ArrayList<>(
			Arrays.asList("AIX and AIXL",
					"AmigaOS",
					"Android ",
					"BSD",
					"Caldera",
					"Chrome OS",
					"Corel Linux",
					"CP/M",
					"Debian Linux",
					"DUnix",
					"DYNIX/ptx",
					"HP-UX",
					"iOS",
					"IRIX",
					"Kondara Linux",
					"Linux",
					"Mac OS 8",
					"Mac OS 9",
					"Mac OS 10",
					"macOS X",
					"Mandriva",
					"MINIX",
					"MS-DOS 1.x",
					"MS-DOS 2.x",
					"MS-DOS 3.x",
					"MS-DOS 4.x",
					"MS-DOS 5.x",
					"MS-DOS 6.x",
					"NEXTSTEP",
					"OS/2",
					"OSF/1",
					"QNX",
					"Red Hat Linux",
					"SCO",
					"Slackware Linux",
					"Sun Solaris",
					"SuSE Linux",
					"Symbian",
					"System 1",
					"System 2",
					"System 3",
					"System 4",
					"System 6",
					"System 7",
					"System V",
					"Tru64 Unix",
					"Turbolinux",
					"Ultrix",
					"Unisys",
					"Unix",
					"UnixWare",
					"VectorLinux",
					"Windows 10",
					"Windows 2000",
					"Windows 2003",
					"Windows 3.X",
					"Windows 7",
					"Windows 8",
					"Windows 95",
					"Windows 98",
					"Windows CE",
					"Windows ME",
					"Windows NT",
					"Windows Vista",
					"Windows XP"));

	private static final List<String> middlewareList = new ArrayList<>(
			Arrays.asList("Apache Tomcat",
					"WildFly",
					"JBoss EAP",
					"Borland Entreprise Server",
					"Citrix",
					"Apache Geronimo",
					"GlassFish",
					"IBM Websphere Application Server",
					"IBM Lotus Domino",
					"JOnAS",
					"Microsoft ASP.NET",
					"LTSP Cluster",
					"Novell exteNd Application Server",
					"Oracle Application Server",
					"Oracle WebLogic Server",
					"Orion Application Server",
					"Resin Server",
					"Sybase EAServer",
					"WebDev PC SOFT",
					"Zope"));
	
	private static final List<String> randomNamesList = new ArrayList<>(Arrays.asList("Albane Lémery",
			"Marie-Françoise Bourgeois",
			"Clémentine Manoury",
			"Aurélie Béliveau",
			"Lucile Montgomery",
			"Débora Chevalier",
			"Godeliève Ponce",
			"Julienne Beauvilliers",
			"Dorothée Brassard",
			"Lydie Rouzet",
			"Mathias Jacquemin",
			"Adolphe Vannier",
			"Vivien Reverdin",
			"Théophile Beaulieu",
			"Ernest Périer",
			"Martin Mallet",
			"Arnaud Schaeffer",
			"Nathan Vidal",
			"Marcel Botrel",
			"Claude Compere",
			"Bérengère Tomas",
			"Philomène Carré",
			"Désirée Rochefort",
			"Marthe Gaudreau",
			"Fabienne Carré",
			"Ingrid Delafose",
			"Laëtitia Sharpe",
			"Germaine Berlioz",
			"Marion Deschanel",
			"Nina Bélanger",
			"Roland Blaise",
			"Benjamin Gérin",
			"Armel Barbet",
			"Théophile Barrande",
			"Nathanaël Rochefort",
			"Robert Dimont",
			"Antoine Doisneau",
			"Alex Delannoy",
			"Olivier De Villiers",
			"Christopher Stuart",
			"Honorine Vannier",
			"Éléonore Bélanger",
			"Hortense Beaufils",
			"Bérénice Mace",
			"Bélise Martin",
			"Roberte Vandame",
			"Marie-Louise Cuvillier",
			"Ségolène Pleimelding",
			"Nicole Bardin",
			"Germaine Ancel",
			"Loïc Toutain",
			"Amand Gavreau",
			"Maxime Rossignol",
			"Cyrille Lemaître",
			"Ghislain Emmanuelli",
			"Max Noir",
			"Jean-Joël Bouhier",
			"Abeau Bain",
			"Serge Barbet",
			"Gérard Figuier",
			"Eugénie Lacan",
			"Rolande Ouvrard",
			"Aude Leclair",
			"Capucine Riqueti",
			"Auriane Vaugeois",
			"Iseult Cuvillier",
			"Romaine Levasseur",
			"Danièle Génin",
			"Maïté Boisselot",
			"Valentine Martin",
			"Fabrice Verninac",
			"Moïse Soyer",
			"Boniface Chappuis",
			"Napoléon Battier",
			"Valentin Galopin",
			"Robert LeMahieu",
			"Roger Allard",
			"Dominique Gouin",
			"Maximilien Cousteau",
			"Gaëtan Bertillon",
			"Roméo Joguet",
			"Alceste Bocuse",
			"Jacob Aveline",
			"Barthélemy Brochard",
			"Théo Bachelet",
			"Fernand Boudreaux",
			"Jérémie Pasquier",
			"Damien De Saint-Pierre",
			"Dylan Lemoine",
			"Lucien Parmentier",
			"Danielle Neri",
			"Roxane Bain",
			"Maéva Lahaye",
			"Éléonore Hérisson",
			"Rose Génin",
			"Marie-José Sartre",
			"Thaïs Prudhomme",
			"Blandine Rousseau",
			"Simone Longchambon",
			"Eulalie Boudon",
			"Djeferson Delannoy",
			"Grégoire Cousteau",
			"Mickaël Morin",
			"Renaud Hector",
			"Nicolas Sartre",
			"Emmanuel Duchemin",
			"Nathanaël Aveline",
			"Kévin Galopin",
			"Martial Bourgeois",
			"Jean-Christophe Lièvremont",
			"Noëlle Lavaud",
			"Léa Jauffret",
			"Émeline Gouin",
			"Gaëlle Marchand",
			"Amélie Leblanc",
			"Nadine Raoult",
			"Marion Lecerf",
			"Brigitte Le Sueur",
			"Hélène Dubost",
			"Christelle Pinchon",
			"Élisée Lazard",
			"Jules Beauvilliers",
			"Alban Beaubois",
			"Bastien Devereux",
			"Vivien Marais",
			"Mathieu Gilson",
			"Gilbert Ménétries",
			"Yvon Hémery",
			"Côme Jacquinot",
			"Pierre-Louis Chauveau",
			"Ingrid Boffrand",
			"Frédérique Demaret",
			"Ameline Gilson",
			"Clothilde Beauvilliers",
			"Yvette Loup",
			"Estelle Duret",
			"Louise Doisneau",
			"Antoinette Cormier",
			"Gisèle Allard",
			"Perrine Anouilh",
			"Gaspard Philippon",
			"Mathias Lahaye",
			"Hugo Bougie",
			"Fernand Gauthier",
			"Matthieu Pomeroy",
			"Aubin Bruguière",
			"Bastien Bonhomme",
			"Baudouin Reverdin",
			"Lucas Bourseiller",
			"Guillaume Lahaye",
			"Barbe Chevotet",
			"Ameline Vernier",
			"Vivienne Lazard",
			"Gabrielle Duhamel",
			"Nadège Asselin",
			"Marie-Claude Galopin",
			"Léonie Lalande",
			"Rachel Pleimelding",
			"Anne-Marie Boulanger",
			"Cécile Leroux",
			"Stéphane Robiquet",
			"Thaddée Courtial",
			"Joseph Lafaille",
			"Félix Bonhomme",
			"Estienne Bertillon",
			"Ghislain Badeaux",
			"Émilien Pueyrredón",
			"Gaby Dumont",
			"Émilien Choquet",
			"Timothée Bourque",
			"Jeanne Grosjean",
			"Solenn Bullion",
			"Lucille Poussin",
			"Laura Paquin",
			"Paulette Gaudin",
			"Yseult Aubert",
			"Mélanie Ange",
			"Jeannine Brazier",
			"Romane Piaget",
			"Simone Chastain",
			"Jean-Baptiste Beaulieu",
			"Hugues Tomas",
			"Richard Neri",
			"Joseph Périer",
			"Cyril Renou",
			"Rémy Prudhomme",
			"José Blanc",
			"Gaétan De Verley",
			"Baptiste Alméras",
			"Igor LeMahieu",
			"Julienne Dubuisson",
			"Lesly Mallette",
			"Cécile Bassot",
			"Suzanne Peletier",
			"Laurine Baumé",
			"Nadège Alméras",
			"Gaëlle Charbonnier",
			"Victoria Plouffe",
			"Victoire Guillaume",
			"Sylvaine Aliker"));

	public File getJsonFilePath(String pathurl) throws IOException {
		File txtFile = new File(pathurl);

		//Get project path
		// get FileSystem separator
		FileSystem fileSystem = FileSystems.getDefault();
		String fileSystemSeparator = fileSystem.getSeparator();

		String rsrcFolder = pathurl.split("db_population")[0];
		String jsonFilePath = rsrcFolder + "db_population_json" + fileSystemSeparator + txtFile.getName() + ".json";

		File jsonFile = new File(jsonFilePath);
		jsonFile.createNewFile();

		return jsonFile;
	}

	public void createUsers(String usernames_txt_file, Long last_id) throws IOException {
		File txtFile = new File(usernames_txt_file);
		if(txtFile.getName().startsWith("users")) {

			File jsonFile = getJsonFilePath(usernames_txt_file);

			FileOutputStream fos = new FileOutputStream(jsonFile);
			BufferedReader reader = new BufferedReader(new FileReader(usernames_txt_file));

			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.getFactory().configure(JsonWriteFeature.ESCAPE_NON_ASCII.mappedFeature(), true);

			String line = reader.readLine();
			String newLine = System.getProperty("line.separator");

			//Get last id from user repository
			Long id = last_id+1;

			fos.write("[".getBytes());
			while (line != null) {
				//Create user
				User user = new User();

				user.setId(id);
				user.setEnabled(true);
				user.setAccountNonExpired(true);
				user.setCredentialsNonExpired(true);
				user.setAccountNonLocked(true);

				user.setUsername(line+EMAILEND);
				user.setPassword(PASSWORD);

				//Set default informations
				UserInfo info = new UserInfo();
				UserCV cv = new UserCV();

				//Random date
				//default time zone
				ZoneId defaultZoneId = ZoneId.systemDefault();

				LocalDate startDate = LocalDate.of(1990, 1, 1); //start date
				long start = startDate.toEpochDay();

				LocalDate endDate = LocalDate.now(); //end date
				long end = endDate.toEpochDay();

				long randomEpochDay = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong(); //random date in the range
				LocalDate localDate = LocalDate.ofEpochDay(randomEpochDay);

				Date out = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

				info.setCus_dob(out);
				info.setCus_email(user.getUsername());
				info.setCus_name(line);
				info.setUserInfo(user);

				Random r = new Random();
				info.setCus_addr(citiesList.get(r.nextInt(citiesList.size())));
				info.setCus_tel(" ");
				cv.setExperience(r.nextInt(20-1) + 1);

				Integer dbChoices = r.nextInt(4-1) + 1;
				Integer frameChoices = r.nextInt(5-1) + 1;
				Integer languageChoices = r.nextInt(5-1) + 1;
				Integer middlewareChoices = r.nextInt(4-1) + 1;
				Integer osChoices = r.nextInt(5-1) + 1;
				String frameworks = "";
				String languages = "";
				String middlewares = "";
				String operatingsystems = "";
				String database = "";
				for(int i = 0; i < frameChoices; i++) {
					frameworks += frameworksList.get(r.nextInt(frameworksList.size())) + ",";
				}
				for(int i = 0; i < languageChoices; i++) {
					languages += languageList.get(r.nextInt(languageList.size())) + ",";
				}
				for(int i = 0; i < middlewareChoices; i++) {
					middlewares += middlewareList.get(r.nextInt(middlewareList.size())) + ",";
				}
				for(int i = 0; i < osChoices; i++) {
					operatingsystems += osList.get(r.nextInt(osList.size())) + ",";
				}
				for(int i = 0; i < dbChoices; i++) {
					database += databaseList.get(r.nextInt(databaseList.size())) + ",";
				}

				cv.setFrameworks(frameworks);
				cv.setLanguages(languages);
				cv.setMiddlewares(middlewares);
				cv.setOperating_system(operatingsystems);
				cv.setDatabasesList(database);

				cv.setUserCV(user);

				user.setUserInfo(info);
				user.setUserCV(cv);

				//Set Authorities
				Authority authority = new Authority();
				authority.setId(2);
				authority.setName(AuthorityType.USER);

				UserAuthority userAuthority = new UserAuthority();
				userAuthority.setUserAuthority(user);
				userAuthority.setAuthority(authority);
				List<UserAuthority> userAuth = new ArrayList<>();
				userAuth.add(userAuthority);

				user.setUserAuthority(userAuth);

				if(!user.getUsername().equals(EMAILEND)) {
					//Convert to json
					String userJson = objectMapper.writeValueAsString(user);
					fos.write(userJson.getBytes());
				}

				// read next line
				line = reader.readLine();
				if(line != null) {
					fos.write(",".getBytes());
					fos.write(newLine.getBytes());
				}

				id++;
			}
			reader.close();

			fos.write("]".getBytes());
			fos.close();
		}
	}

	public void createClientInfos(String usernames_txt_file, Long client_info_last_id) throws IOException {
		File txtFile = new File(usernames_txt_file);
		if(txtFile.getName().startsWith("clientinfos")) {

			File jsonFile = getJsonFilePath(usernames_txt_file);

			FileOutputStream fos = new FileOutputStream(jsonFile);
			BufferedReader reader = new BufferedReader(new FileReader(usernames_txt_file));

			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.getFactory().configure(JsonWriteFeature.ESCAPE_NON_ASCII.mappedFeature(), true);

			String line = reader.readLine();
			String newLine = System.getProperty("line.separator");

			//Get last id from clientInfo repository
			Long id = client_info_last_id+1;
			
			//Get last id from department repository
			//Long departmentId = deparment_last_id+1;
			
			List<String> departmentDescriptionList = new ArrayList<>(
					Arrays.asList("security",
									"middleware",
									"management",
									"dba",
									"cloud management",
									"infrastructure",
									"devops",
									"risk management",
									"development",
									"network",
									"user experience",
									"surveillance&monitoring management"));
			fos.write("[".getBytes());
			while (line != null) {
				Random r = new Random();

				ClientInfo clientInfo = new ClientInfo();
				clientInfo.setId(id);
				clientInfo.setName(line);
				clientInfo.setLocation(citiesList.get(r.nextInt(citiesList.size())));
				clientInfo.setTelephone(" ");

				Integer departmentsNumber = r.nextInt(5-1) + 1;
				List<Department> departmentsList = new ArrayList<>();
				for(int j = 0; j < departmentsNumber; j++) {
					Department department = new Department();
					String departmentName = "";
					Integer lettersNumber = r.nextInt(5-1) + 1;
					for(int i = 0; i < lettersNumber; i++) {
						departmentName += (char) ((int)(Math.random()*100)%26+65);
					}
					departmentName += " at "+line + "(identifier :"+String.valueOf(r.nextInt(10000-1)+1)+")";
					department.setName(departmentName);
					department.setDescription(line+ " "+departmentDescriptionList.get(r.nextInt(departmentDescriptionList.size())));
					department.setClientinfo(clientInfo);
					
					Integer teamsNumber = r.nextInt(5-1) + 1;
					List<Team> teamsList = new ArrayList<>();
					for(int l = 0; l < teamsNumber; l++) {
						Team team = new Team();
						team.setHead(randomNamesList.get(r.nextInt(randomNamesList.size())));
						team.setDepartment(department);
						
						teamsList.add(team);
						
					}
					
					department.setTeams(teamsList);
					
					departmentsList.add(department);
						
				}
				
				clientInfo.setDepartments(departmentsList);
				
				//Convert to json
				String clientInfoJson = objectMapper.writeValueAsString(clientInfo);
				fos.write(clientInfoJson.getBytes());
				
				// read next line
				line = reader.readLine();
				if(line != null) {
					fos.write(",".getBytes());
					fos.write(newLine.getBytes());
				}

				id++;
			}

			reader.close();

			fos.write("]".getBytes());
			fos.close();
		}

	}

}
