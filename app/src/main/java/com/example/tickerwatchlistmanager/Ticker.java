package com.example.tickerwatchlistmanager;

public class Ticker {
    private String symbol;
    private Float price;
    private Float  beta;
    private Float volAvg;
    private Float mktCap;
    private Float lastDiv;
    private String range;
    private Float changes;
    private String companyName;
    private  String currency;
    private String cik;
    private String  isin;
    private String  cusip;
    private String  exchange;
    private String  exchangeShortName;
    private String  industry;
    private String  website;
    private  String description;
    private String  ceo;
    private  String sector;
    private  String country;
    private  String fullTimeEmployees;
    private  String phone;
    private  String address;
    private String  city;
    private String  state;
    private  String zip;
    private Float dcfDiff;
    private Float dcf;
    private String  image;
    private  String  ipoDate;
    private boolean defaultImage;
    private boolean isEtf;
    private boolean  isActivelyTrading;

    public Ticker(String symbol, Float price, Float beta, Float volAvg, Float mktCap, Float lastDiv,
                  String range, Float changes, String companyName, String currency, String cik,
                  String isin, String cusip, String exchange, String exchangeShortName, String industry,
                  String website, String description, String ceo, String sector, String country,
                  String fullTimeEmployees, String phone, String address, String city, String state,
                  String zip, Float dcfDiff, Float dcf, String image, String ipoDate, boolean defaultImage,
                  boolean isEtf, boolean isActivelyTrading) {
        this.symbol = symbol;
        this.price = price;
        this.beta = beta;
        this.volAvg = volAvg;
        this.mktCap = mktCap;
        this.lastDiv = lastDiv;
        this.range = range;
        this.changes = changes;
        this.companyName = companyName;
        this.currency = currency;
        this.cik = cik;
        this.isin = isin;
        this.cusip = cusip;
        this.exchange = exchange;
        this.exchangeShortName = exchangeShortName;
        this.industry = industry;
        this.website = website;
        this.description = description;
        this.ceo = ceo;
        this.sector = sector;
        this.country = country;
        this.fullTimeEmployees = fullTimeEmployees;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.dcfDiff = dcfDiff;
        this.dcf = dcf;
        this.image = image;
        this.ipoDate = ipoDate;
        this.defaultImage = defaultImage;
        this.isEtf = isEtf;
        this.isActivelyTrading = isActivelyTrading;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getBeta() {
        return beta;
    }

    public void setBeta(Float beta) {
        this.beta = beta;
    }

    public Float getVolAvg() {
        return volAvg;
    }

    public void setVolAvg(Float volAvg) {
        this.volAvg = volAvg;
    }

    public Float getMktCap() {
        return mktCap;
    }

    public void setMktCap(Float mktCap) {
        this.mktCap = mktCap;
    }

    public Float getLastDiv() {
        return lastDiv;
    }

    public void setLastDiv(Float lastDiv) {
        this.lastDiv = lastDiv;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public Float getChanges() {
        return changes;
    }

    public void setChanges(Float changes) {
        this.changes = changes;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCik() {
        return cik;
    }

    public void setCik(String cik) {
        this.cik = cik;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public String getCusip() {
        return cusip;
    }

    public void setCusip(String cusip) {
        this.cusip = cusip;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getExchangeShortName() {
        return exchangeShortName;
    }

    public void setExchangeShortName(String exchangeShortName) {
        this.exchangeShortName = exchangeShortName;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCeo() {
        return ceo;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFullTimeEmployees() {
        return fullTimeEmployees;
    }

    public void setFullTimeEmployees(String fullTimeEmployees) {
        this.fullTimeEmployees = fullTimeEmployees;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Float getDcfDiff() {
        return dcfDiff;
    }

    public void setDcfDiff(Float dcfDiff) {
        this.dcfDiff = dcfDiff;
    }

    public Float getDcf() {
        return dcf;
    }

    public void setDcf(Float dcf) {
        this.dcf = dcf;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIpoDate() {
        return ipoDate;
    }

    public void setIpoDate(String ipoDate) {
        this.ipoDate = ipoDate;
    }

    public boolean isDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(boolean defaultImage) {
        this.defaultImage = defaultImage;
    }

    public boolean isEtf() {
        return isEtf;
    }

    public void setEtf(boolean etf) {
        isEtf = etf;
    }

    public boolean isActivelyTrading() {
        return isActivelyTrading;
    }

    public void setActivelyTrading(boolean activelyTrading) {
        isActivelyTrading = activelyTrading;
    }
}
