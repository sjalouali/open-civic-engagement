import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { OpenCivicEngagementTestModule } from '../../../test.module';
import { CommentOceOceDetailComponent } from 'app/entities/comment-oce-oce/comment-oce-oce-detail.component';
import { CommentOceOce } from 'app/shared/model/comment-oce-oce.model';

describe('Component Tests', () => {
  describe('CommentOceOce Management Detail Component', () => {
    let comp: CommentOceOceDetailComponent;
    let fixture: ComponentFixture<CommentOceOceDetailComponent>;
    let dataUtils: JhiDataUtils;
    const route = ({ data: of({ commentOce: new CommentOceOce(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [OpenCivicEngagementTestModule],
        declarations: [CommentOceOceDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CommentOceOceDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CommentOceOceDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = fixture.debugElement.injector.get(JhiDataUtils);
    });

    describe('OnInit', () => {
      it('Should load commentOce on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.commentOce).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });

    describe('byteSize', () => {
      it('Should call byteSize from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'byteSize');
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.byteSize(fakeBase64);

        // THEN
        expect(dataUtils.byteSize).toBeCalledWith(fakeBase64);
      });
    });

    describe('openFile', () => {
      it('Should call openFile from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'openFile');
        const fakeContentType = 'fake content type';
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.openFile(fakeContentType, fakeBase64);

        // THEN
        expect(dataUtils.openFile).toBeCalledWith(fakeContentType, fakeBase64);
      });
    });
  });
});
