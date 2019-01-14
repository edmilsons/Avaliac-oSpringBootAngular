export class ConfigService {
    
    private urlService:string;

    constructor(){

        this.urlService = 'http://localhost:9000/service';
    }

    getUrlService(): string {

        return this.urlService;
    }
    
}