import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { JhipsterTestModule } from '../../../test.module';
import { OrganisationOceDetailComponent } from 'app/entities/organisation-oce/organisation-oce-detail.component';
import { OrganisationOce } from 'app/shared/model/organisation-oce.model';

describe('Component Tests', () => {
  describe('OrganisationOce Management Detail Component', () => {
    let comp: OrganisationOceDetailComponent;
    let fixture: ComponentFixture<OrganisationOceDetailComponent>;
    let dataUtils: JhiDataUtils;
    const route = ({ data: of({ organisation: new OrganisationOce(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterTestModule],
        declarations: [OrganisationOceDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(OrganisationOceDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OrganisationOceDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = fixture.debugElement.injector.get(JhiDataUtils);
    });

    describe('OnInit', () => {
      it('Should load organisation on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.organisation).toEqual(jasmine.objectContaining({ id: 123 }));
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
