import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { OrganisationOceService } from 'app/entities/organisation-oce/organisation-oce.service';
import { IOrganisationOce, OrganisationOce } from 'app/shared/model/organisation-oce.model';
import { LegalStatus } from 'app/shared/model/enumerations/legal-status.model';

describe('Service Tests', () => {
  describe('OrganisationOce Service', () => {
    let injector: TestBed;
    let service: OrganisationOceService;
    let httpMock: HttpTestingController;
    let elemDefault: IOrganisationOce;
    let expectedResult: IOrganisationOce | IOrganisationOce[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(OrganisationOceService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new OrganisationOce(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        LegalStatus.NON_PROFIT_ORGANIZATION
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a OrganisationOce', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new OrganisationOce()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a OrganisationOce', () => {
        const returnedFromService = Object.assign(
          {
            uuid: 'BBBBBB',
            uuidEntity: 'BBBBBB',
            name: 'BBBBBB',
            description: 'BBBBBB',
            legalId: 'BBBBBB',
            accreditation: 'BBBBBB',
            additionalInformation: 'BBBBBB',
            legalStatus: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of OrganisationOce', () => {
        const returnedFromService = Object.assign(
          {
            uuid: 'BBBBBB',
            uuidEntity: 'BBBBBB',
            name: 'BBBBBB',
            description: 'BBBBBB',
            legalId: 'BBBBBB',
            accreditation: 'BBBBBB',
            additionalInformation: 'BBBBBB',
            legalStatus: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a OrganisationOce', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
