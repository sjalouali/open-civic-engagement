import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { CommentOceOceService } from 'app/entities/comment-oce-oce/comment-oce-oce.service';
import { ICommentOceOce, CommentOceOce } from 'app/shared/model/comment-oce-oce.model';

describe('Service Tests', () => {
  describe('CommentOceOce Service', () => {
    let injector: TestBed;
    let service: CommentOceOceService;
    let httpMock: HttpTestingController;
    let elemDefault: ICommentOceOce;
    let expectedResult: ICommentOceOce | ICommentOceOce[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(CommentOceOceService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new CommentOceOce(0, 'AAAAAAA', currentDate, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', false, false);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            commentDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a CommentOceOce', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            commentDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            commentDate: currentDate
          },
          returnedFromService
        );

        service.create(new CommentOceOce()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a CommentOceOce', () => {
        const returnedFromService = Object.assign(
          {
            content: 'BBBBBB',
            commentDate: currentDate.format(DATE_TIME_FORMAT),
            uuidOce: 'BBBBBB',
            uuidOrganisation: 'BBBBBB',
            uuidEntity: 'BBBBBB',
            archived: true,
            deleted: true
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            commentDate: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of CommentOceOce', () => {
        const returnedFromService = Object.assign(
          {
            content: 'BBBBBB',
            commentDate: currentDate.format(DATE_TIME_FORMAT),
            uuidOce: 'BBBBBB',
            uuidOrganisation: 'BBBBBB',
            uuidEntity: 'BBBBBB',
            archived: true,
            deleted: true
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            commentDate: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a CommentOceOce', () => {
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
