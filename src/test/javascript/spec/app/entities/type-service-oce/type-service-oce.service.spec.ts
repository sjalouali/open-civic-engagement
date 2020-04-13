import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TypeServiceOceService } from 'app/entities/type-service-oce/type-service-oce.service';
import { ITypeServiceOce, TypeServiceOce } from 'app/shared/model/type-service-oce.model';

describe('Service Tests', () => {
  describe('TypeServiceOce Service', () => {
    let injector: TestBed;
    let service: TypeServiceOceService;
    let httpMock: HttpTestingController;
    let elemDefault: ITypeServiceOce;
    let expectedResult: ITypeServiceOce | ITypeServiceOce[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TypeServiceOceService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new TypeServiceOce(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', false, false);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TypeServiceOce', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TypeServiceOce()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TypeServiceOce', () => {
        const returnedFromService = Object.assign(
          {
            nameShort: 'BBBBBB',
            nameLong: 'BBBBBB',
            description: 'BBBBBB',
            typeServiceColor: 'BBBBBB',
            uuidOce: 'BBBBBB',
            uuidOrganisation: 'BBBBBB',
            uuidEntity: 'BBBBBB',
            archived: true,
            deleted: true
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TypeServiceOce', () => {
        const returnedFromService = Object.assign(
          {
            nameShort: 'BBBBBB',
            nameLong: 'BBBBBB',
            description: 'BBBBBB',
            typeServiceColor: 'BBBBBB',
            uuidOce: 'BBBBBB',
            uuidOrganisation: 'BBBBBB',
            uuidEntity: 'BBBBBB',
            archived: true,
            deleted: true
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

      it('should delete a TypeServiceOce', () => {
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
