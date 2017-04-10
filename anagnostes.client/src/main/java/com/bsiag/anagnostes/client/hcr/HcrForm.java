package com.bsiag.anagnostes.client.hcr;

import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.dto.FormData.SdkCommand;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.imagefield.AbstractImageField;
import org.eclipse.scout.rt.client.ui.form.fields.sequencebox.AbstractSequenceBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.resource.BinaryResource;
import org.eclipse.scout.rt.shared.AbstractIcons;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

import com.bsiag.anagnostes.client.hcr.HcrForm.MainBox.ImageGroupBox.PaperFormImageField;
import com.bsiag.anagnostes.client.hcr.HcrForm.MainBox.RecognitionBox.Output0Field;
import com.bsiag.anagnostes.client.hcr.HcrForm.MainBox.RecognitionBox.Output1Field;
import com.bsiag.anagnostes.client.hcr.HcrForm.MainBox.RecognitionBox.Output2Field;
import com.bsiag.anagnostes.client.hcr.HcrForm.MainBox.RecognitionBox.Output3Field;
import com.bsiag.anagnostes.client.hcr.HcrForm.MainBox.RecognitionBox.Output4Field;
import com.bsiag.anagnostes.client.hcr.HcrForm.MainBox.RecognitionBox.Output5Field;
import com.bsiag.anagnostes.client.hcr.HcrForm.MainBox.RecognitionBox.Output6Field;
import com.bsiag.anagnostes.client.hcr.HcrForm.MainBox.RecognitionBox.Output7Field;
import com.bsiag.anagnostes.client.hcr.HcrForm.MainBox.RecognitionBox.Output8Field;
import com.bsiag.anagnostes.client.hcr.HcrForm.MainBox.RecognitionBox.Output9Field;
import com.bsiag.anagnostes.shared.hcr.HcrFormData;
import com.bsiag.anagnostes.shared.hcr.IHcrService;
import com.bsiag.anagnostes.shared.hcr.ScanLookupCall;
import org.eclipse.scout.rt.platform.classid.ClassId;

/**
 * <h3>{@link HcrForm}</h3>
 *
 * @author cbu
 */
@ClassId("a2e2502e-d73e-471e-a95a-d66ed33c856b")
@FormData(value = HcrFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class HcrForm extends AbstractForm {

	public HcrForm() {
		setHandler(new ViewHandler());
	}

	private BinaryResource m_esrFormImage;

	@FormData
	public BinaryResource getEsrFormImage() {
		return m_esrFormImage;
	}

	@FormData
	public void setEsrFormImage(BinaryResource esrFormImage) {
		m_esrFormImage = esrFormImage;
	}

	@Override
	protected boolean getConfiguredAskIfNeedSave() {
		return false;
	}

	@Override
	protected int getConfiguredModalityHint() {
		return MODALITY_HINT_MODELESS;
	}

	@Override
	protected String getConfiguredIconId() {
		return AbstractIcons.World;
	}

	public MainBox getMainBox() {
		return getFieldByClass(MainBox.class);
	}

	public PaperFormImageField getPaperFormImageField() {
		return getFieldByClass(PaperFormImageField.class);
	}

	public Output0Field getOutput0Field() {
		return getFieldByClass(Output0Field.class);
	}

	public Output1Field getOutput1Field() {
		return getFieldByClass(Output1Field.class);
	}

	public Output2Field getOutput2Field() {
		return getFieldByClass(Output2Field.class);
	}

	public Output3Field getOutput3Field() {
		return getFieldByClass(Output3Field.class);
	}

	public Output4Field getOutput4Field() {
		return getFieldByClass(Output4Field.class);
	}

	public Output5Field getOutput5Field() {
		return getFieldByClass(Output5Field.class);
	}

	public Output6Field getOutput6Field() {
		return getFieldByClass(Output6Field.class);
	}

	public Output7Field getOutput7Field() {
		return getFieldByClass(Output7Field.class);
	}

	public Output8Field getOutput8Field() {
		return getFieldByClass(Output8Field.class);
	}

	public Output9Field getOutput9Field() {
		return getFieldByClass(Output9Field.class);
	}

	@Override
	protected void execInitForm() {
		reload();
	}

	public void reload() {
		IHcrService service = BEANS.get(IHcrService.class);
		HcrFormData formData = new HcrFormData();
		exportFormData(formData);
		formData = service.load(formData);
		importFormData(formData);
		getPaperFormImageField().setImage(formData.getEsrFormImage().getContent());

		getOutput0Field().getConfidenceField().updateColor();
		getOutput1Field().getConfidenceField().updateColor();
		getOutput2Field().getConfidenceField().updateColor();
		getOutput3Field().getConfidenceField().updateColor();
		getOutput4Field().getConfidenceField().updateColor();
		getOutput5Field().getConfidenceField().updateColor();
		getOutput6Field().getConfidenceField().updateColor();
		getOutput7Field().getConfidenceField().updateColor();
		getOutput8Field().getConfidenceField().updateColor();
		getOutput9Field().getConfidenceField().updateColor();
	}

	@Order(1000)
	@ClassId("f9707057-c6bc-46a2-a07f-f234415ff2ea")
	public class MainBox extends AbstractGroupBox {

		@Order(1000)
		@ClassId("f830181a-85bd-4ad9-beff-c6c3852dd145")
		public class GroupBox extends AbstractGroupBox {

			@Override
			protected int getConfiguredGridColumnCount() {
				return 1;
			}

			@Order(1000)
			@ClassId("b462c4c6-834d-4064-a10b-60848b063600")
			public class SequenceBox extends AbstractSequenceBox {

				@Override
				protected boolean getConfiguredLabelVisible() {
					return false;
				}

				@Override
				protected boolean getConfiguredFillHorizontal() {
					return false;
				}

				@Override
				protected boolean getConfiguredEqualColumnWidths() {
					return false;
				}

				@Override
				protected boolean getConfiguredAutoCheckFromTo() {
					return false;
				}

				@Order(1000)
				@ClassId("e05db9c9-08b2-4f8a-b271-5684aec9e69b")
				public class ScanField extends AbstractSmartField<String> {

					@Override
					protected boolean getConfiguredLabelVisible() {
						return false;
					}

					@Override
					protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
						return ScanLookupCall.class;
					}

					@Override
					protected boolean getConfiguredFillHorizontal() {
						return true;
					}

					@Override
					protected int getConfiguredWidthInPixel() {
						return 250;
					}
				}

				@Order(2000)
				@ClassId("f3f70639-a772-4e19-b4f6-a245ca1eb92c")
				public class ReloadButton extends AbstractButton {

					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("NextScan");
					}

					@Override
					protected boolean getConfiguredProcessButton() {
						return false;
					}

					@Override
					protected void execClickAction() {
						reload();
					}
				}
			}
		}

		@Order(3000)
		@ClassId("e5ef0799-6b94-4b8d-85c6-8989b8a50cfb")
		public class ImageGroupBox extends AbstractGroupBox {

			@Order(1000)
			@ClassId("0b81be1f-b3f7-448f-8d92-1078a913389d")
			public class PaperFormImageField extends AbstractImageField {

				@Override
				protected boolean getConfiguredLabelVisible() {
					return false;
				}

				@Override
				protected boolean getConfiguredFillHorizontal() {
					return true;
				}

				@Override
				protected int getConfiguredHeightInPixel() {
					return 350;
				}
			}
		}

		@Order(4000)
		@ClassId("da9c54b0-95e6-4289-8944-4e14a044be85")
		public class RecognitionBox extends AbstractSequenceBox {

			@Override
			protected boolean getConfiguredLabelVisible() {
				return false;
			}

			@Override
			protected int getConfiguredGridW() {
				return 2;
			}

			@Order(1000)
			@ClassId("77259e4b-5df5-4cde-8ae6-352130253fd2")
			public class Output0Field extends AbstractOutputField {
			}

			@Order(2000)
			@ClassId("b92bcdeb-b9c7-4d97-8f51-ddd08b5babc8")
			public class Output1Field extends AbstractOutputField {
			}

			@Order(3000)
			@ClassId("f7ba1424-c1c2-4e73-981b-cbebb002c190")
			public class Output2Field extends AbstractOutputField {
			}

			@Order(4000)
			@ClassId("5cd6f670-97d3-4316-8921-4b682478ce73")
			public class Output3Field extends AbstractOutputField {
			}

			@Order(5000)
			@ClassId("5c172ee9-8b45-4a50-ae88-7c5b86e3d495")
			public class Output4Field extends AbstractOutputField {
			}

			@Order(6000)
			@ClassId("08178dca-398f-4d38-a8dd-e4d8489e76ca")
			public class Output5Field extends AbstractOutputField {
			}

			@Order(7000)
			@ClassId("bc74f27a-f2f9-4518-b308-7c4ba1abf41b")
			public class Output6Field extends AbstractOutputField {
			}

			@Order(8000)
			@ClassId("e2433709-b657-4547-967c-5bd94a69abff")
			public class Output7Field extends AbstractOutputField {
			}

			@Order(9000)
			@FormData(sdkCommand = SdkCommand.IGNORE)
			@ClassId("b0363967-e5ed-4a1e-b04a-412bedf3e5d3")
			public class DotField extends AbstractOutputField {
				@Override
				protected void execInitField() {
					getOutputValueField().setValue(".");
					getOutputValueField().setEnabled(false);
					getConfidenceField().setValue("-");
					getConfidenceField().setEnabled(false);
				}
			}

			@Order(10000)
			@ClassId("6c7e2ffd-d38f-43bf-988b-8021bbe3528d")
			public class Output8Field extends AbstractOutputField {
			}

			@Order(11000)
			@ClassId("3269959e-4ac2-4bb4-9074-1e1fa8638f62")
			public class Output9Field extends AbstractOutputField {
			}
		}
	}

	public class ViewHandler extends AbstractFormHandler {
	}
}
