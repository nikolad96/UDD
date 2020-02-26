package udd.server.controller;

import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.text.PDFTextStripper;
import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import udd.server.dto.MoreLikeThisDTO;
import udd.server.dto.MultiFieldQueryDTO;
import udd.server.dto.QueryDTO;
import udd.server.dto.RadDTO;
import udd.server.model.Rad;
import udd.server.model.RadSearch;
import udd.server.model.Recenzent;
import udd.server.model.RecenzentSearch;
import udd.server.repository.RadRepository;
import udd.server.repository.RecenzentRepository;
import udd.server.repository.elastic.SearchRadRepository;
import udd.server.repository.elastic.SearchRecenzentRepository;
import udd.server.search.SearchService;
import udd.server.service.RadService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private RadService radService;

    @Autowired
    private SearchService searchService;

    @Autowired
    private RadRepository radRepository;

    @Autowired
    private SearchRadRepository searchRadRepository;

    @Autowired
    private SearchRecenzentRepository searchRecenzentRepository;

    @Autowired
    private RecenzentRepository recenzentRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/", produces = "application/json")
    public @ResponseBody
    ResponseEntity<List<RadSearch>> radovi() {
        //provera da li korisnik sa id-jem pera postoji
        //List<User> users = identityService.createUserQuery().userId("pera").list();

        RadSearch radSearch = new RadSearch();
        List<RadSearch> radSearches = new ArrayList<>();
        List<Rad> rads = radRepository.findAll();
        for(Rad r : rads){
            radSearch = new RadSearch();
            radSearch.setAbstrakt(r.getApstrakt());
            radSearch.setAutor(r.getAutor());
            radSearch.setId(r.getId().toString());
            radSearch.setNaziv(r.getNaziv());
            radSearch.setOblast(r.getOblast());
            radSearch.setKljucne(r.getKljucne());
            radSearches.add(radSearch);
            searchRadRepository.save(radSearch);

        }

        RecenzentSearch recenzentSearch = new RecenzentSearch();
        List<RecenzentSearch> recenzentSearches = new ArrayList<>();
        List<Recenzent> recenzents = recenzentRepository.findAll();
        for(Recenzent rec : recenzents){
            recenzentSearch = new RecenzentSearch();
            recenzentSearch.setIme(rec.getIme());
            recenzentSearch.setId(rec.getId().toString());
            GeoPoint loc = new GeoPoint(rec.getLatitude(),rec.getLongitude());
            recenzentSearch.setLocation(loc);
            recenzentSearches.add(recenzentSearch);
            searchRecenzentRepository.save(recenzentSearch);


        }



        return new ResponseEntity<>(radSearches, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, path = "/get", produces = "application/json")
    public @ResponseBody
    ResponseEntity<Iterable<RadSearch>> getAll() {
        //provera da li korisnik sa id-jem pera postoji
        //List<User> users = identityService.createUserQuery().userId("pera").list();

       Iterable<RadSearch> radovi = searchRadRepository.findAll();



        return new ResponseEntity<>(radovi, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/get/rec", produces = "application/json")
    public @ResponseBody
    ResponseEntity<Iterable<RecenzentSearch>> getAllRec() {
        //provera da li korisnik sa id-jem pera postoji
        //List<User> users = identityService.createUserQuery().userId("pera").list();

        Iterable<RecenzentSearch> radovi = searchRecenzentRepository.findAll();



        return new ResponseEntity<>(radovi, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/get1", produces = "application/json")
    public @ResponseBody
    ResponseEntity<Iterable<RadSearch>> get1() {
        //provera da li korisnik sa id-jem pera postoji
        //List<User> users = identityService.createUserQuery().userId("pera").list();

        QueryDTO queryDTO = new QueryDTO();
        queryDTO.setPolje("naziv");
        queryDTO.setVrednost("Rad3");
        List<RadSearch> radovi = searchService.search(queryDTO);



        return new ResponseEntity<>(radovi, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/delete", produces = "application/json")
    public @ResponseBody
    ResponseEntity<Iterable<RadSearch>> deleteAll() {
        //provera da li korisnik sa id-jem pera postoji
        //List<User> users = identityService.createUserQuery().userId("pera").list();
        searchRadRepository.deleteAll();
        Iterable<RadSearch> radovi = searchRadRepository.findAll();


        return new ResponseEntity<>(radovi, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/get/radovi", produces = "application/json")
    public @ResponseBody
    ResponseEntity<List<Rad>> getRadovi() {
        //provera da li korisnik sa id-jem pera postoji
        //List<User> users = identityService.createUserQuery().userId("pera").list();
        List<Rad> radovi = radRepository.findAll();


        return new ResponseEntity<>(radovi, HttpStatus.OK);
    }

//    @RequestMapping(value  = "/newCustomer", method = RequestMethod.POST)
//    private ResponseEntity<CustomerResponseDTO> newCustomer(@RequestBody CustomerRequestDTO customerRequestDTO){
//
//
//
//        return new ResponseEntity<CustomerResponseDTO>(new CustomerResponseDTO(NEW_CUSTOMER_URL, customer.getId()), HttpStatus.OK);
//    }

    @PostMapping(path = "/post/file/{radId}", produces = "application/json")
    public @ResponseBody
    ResponseEntity postPodaci(@RequestParam("file") MultipartFile pdfFile , @PathVariable String radId) throws IOException {
        Rad rad = radRepository.findOneById(Long.parseLong(radId));
        rad = radService.savePdf(pdfFile,rad);

        System.out.println(pdfFile.getContentType());
        System.out.println(pdfFile.getName());
        System.out.println(pdfFile.getOriginalFilename());
        System.out.println(pdfFile.getResource());

        FileOutputStream fop = null;
        File file;

        try {

            file = new File("c:/UDD/"+pdfFile.getOriginalFilename()+".pdf");
            fop = new FileOutputStream(file);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            // get the content in bytes


            fop.write(pdfFile.getBytes());
            fop.flush();
            fop.close();

            PDFParser pdfParser = new PDFParser(new RandomAccessFile(file, "r"));
            pdfParser.parse();
            PDFTextStripper pdfTextStripper = new PDFTextStripper();

            String content = pdfTextStripper.getText(pdfParser.getPDDocument());
            System.out.println(content);
            content = content.replace("\n", "").replace("\r", "");

            RadSearch radSearch = searchRadRepository.findOneById(radId);
            radSearch.setText(content);
            searchRadRepository.save(radSearch);
            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }







        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value  = "/search", method = RequestMethod.POST)
    private ResponseEntity<List<RadSearch>> search1(@RequestBody QueryDTO queryDTO){
        List<RadSearch> radSearches = searchService.search(queryDTO);
        System.out.println(queryDTO.getVrednost());

        return new ResponseEntity<>(radSearches, HttpStatus.OK);
    }
    @RequestMapping(value  = "/more", method = RequestMethod.POST)
    private ResponseEntity<List<RadSearch>> more(@RequestBody MoreLikeThisDTO moreLikeThisDTO){

        RadSearch radSearch = searchRadRepository.findOneById(moreLikeThisDTO.getVrednost());
        String tekst = radSearch.getText();
        moreLikeThisDTO.setVrednost(tekst);
        //moreLikeThisDTO.setVrednost("Lorem ipsum");
        List<RadSearch> radSearches = searchService.searchMore(moreLikeThisDTO);
        System.out.println(moreLikeThisDTO.getVrednost());

        System.out.println(moreLikeThisDTO.getMax());
        System.out.println(moreLikeThisDTO.getMin());
        return new ResponseEntity<>(radSearches, HttpStatus.OK);
    }
    @RequestMapping(value  = "/geo", method = RequestMethod.POST)
    private ResponseEntity<List<RecenzentSearch>> geo(@RequestBody RadDTO radDTO){

        Rad rad = radRepository.findOneById(radDTO.getId());
        GeoPoint geoPoint = new GeoPoint(rad.getLat(), rad.getLon());
        List<RecenzentSearch> recenzentSearches = searchService.searchGeo(geoPoint);

        return new ResponseEntity<>(recenzentSearches, HttpStatus.OK);
    }
    @RequestMapping(value  = "/multi", method = RequestMethod.POST)
    private ResponseEntity<List<RadSearch>> multiField(@RequestBody MultiFieldQueryDTO multiFieldQueryDTO){
        List<RadSearch> radSearches = searchService.searchMulti(multiFieldQueryDTO);


        return new ResponseEntity<>(radSearches, HttpStatus.OK);
    }
}
