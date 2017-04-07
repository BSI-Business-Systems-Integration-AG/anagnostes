package com.bsiag.anagnostes.demoapp.client.hcr;

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

import com.bsiag.anagnostes.demoapp.client.hcr.HcrForm.MainBox.ImageGroupBox.PaperFormImageField;
import com.bsiag.anagnostes.demoapp.client.hcr.HcrForm.MainBox.RecognitionBox.Output0Field;
import com.bsiag.anagnostes.demoapp.client.hcr.HcrForm.MainBox.RecognitionBox.Output1Field;
import com.bsiag.anagnostes.demoapp.client.hcr.HcrForm.MainBox.RecognitionBox.Output2Field;
import com.bsiag.anagnostes.demoapp.client.hcr.HcrForm.MainBox.RecognitionBox.Output3Field;
import com.bsiag.anagnostes.demoapp.client.hcr.HcrForm.MainBox.RecognitionBox.Output4Field;
import com.bsiag.anagnostes.demoapp.client.hcr.HcrForm.MainBox.RecognitionBox.Output5Field;
import com.bsiag.anagnostes.demoapp.client.hcr.HcrForm.MainBox.RecognitionBox.Output6Field;
import com.bsiag.anagnostes.demoapp.client.hcr.HcrForm.MainBox.RecognitionBox.Output7Field;
import com.bsiag.anagnostes.demoapp.client.hcr.HcrForm.MainBox.RecognitionBox.Output8Field;
import com.bsiag.anagnostes.demoapp.client.hcr.HcrForm.MainBox.RecognitionBox.Output9Field;
import com.bsiag.anagnostes.demoapp.shared.hcr.IHcrService;
import com.bsiag.anagnostes.demoapp.shared.hcr.TestPersonLookupCall;
import com.bsiag.anagnostes.demoapp.shared.helloworld.HcrFormData;

/**
 * <h3>{@link HcrForm}</h3>
 *
 * @author cbu
 */
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
	public class MainBox extends AbstractGroupBox {

		@Order(1000)
		public class GroupBox extends AbstractGroupBox {

			@Override
			protected int getConfiguredGridColumnCount() {
				return 1;
			}

			@Order(1000)
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
				public class TestPersonField extends AbstractSmartField<String> {

					@Override
					protected boolean getConfiguredLabelVisible() {
						return false;
					}

					@Override
					protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
						return TestPersonLookupCall.class;
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
		public class ImageGroupBox extends AbstractGroupBox {

			@Order(1000)
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
			public class Output0Field extends AbstractOutputField {
			}

			@Order(2000)
			public class Output1Field extends AbstractOutputField {
			}

			@Order(3000)
			public class Output2Field extends AbstractOutputField {
			}

			@Order(4000)
			public class Output3Field extends AbstractOutputField {
			}

			@Order(5000)
			public class Output4Field extends AbstractOutputField {
			}

			@Order(6000)
			public class Output5Field extends AbstractOutputField {
			}

			@Order(7000)
			public class Output6Field extends AbstractOutputField {
			}

			@Order(8000)
			public class Output7Field extends AbstractOutputField {
			}

			@Order(9000)
			@FormData(sdkCommand = SdkCommand.IGNORE)
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
			public class Output8Field extends AbstractOutputField {
			}

			@Order(11000)
			public class Output9Field extends AbstractOutputField {
			}
		}
	}

	public class ViewHandler extends AbstractFormHandler {
	}
}
